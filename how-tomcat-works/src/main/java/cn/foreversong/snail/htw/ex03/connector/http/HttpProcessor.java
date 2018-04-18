package cn.foreversong.snail.htw.ex03.connector.http;


import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 12:43
 * Description: 处理类
 */
public class HttpProcessor {

    /**
     * 连接器
     */
    private HttpConnector httpConnector = null;

    /**
     * 请求行
     */
    private HttpRequestLine requestLine;
    /**
     * Http 请求
     */
    private HttpRequest request;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    /**
     * Http 处理方法
     *
     * @param socket
     */
    public void process(Socket socket) {

    }

    /**
     * 解析请求行(例如：GET /myApp/ModernServlet?userName=tarzan&password=pwd HTTP/1.1)方法
     *
     * @param in
     * @param out
     */
    private void parseRequest(SocketInputStream in, OutputStream out) throws IOException, ServletException {
        in.readRequestLine(requestLine);    // 解析请求行
        String method = new String(requestLine.method, 0, requestLine.methodEnd); // 请求方法
        String uri = null;  // 请求uri
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);   // 请求协议
        // 校验请求报文
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (requestLine.methodEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }
        // 解析 查询字符串和uri
        int question = requestLine.indexOf("?");
        if (question > 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.methodEnd - question + 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        // 判断是否是绝对路径
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }
        // 解析 Session信息
        String match = ";jssesionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon > 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        }

        // uri容错处理
        String normalizedUri = normalize(uri);
        // 设置请求方法
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocol);
        // 判断URI是否正确
        if (normalizedUri != null) {
            ((HttpRequest) request).setRequestURI(normalizedUri);
        } else {
            ((HttpRequest) request).setRequestURI(uri);
        }
        if (normalizedUri == null) {
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }

    /**
     * Return a context-relative path, beginning with a "/", that represents
     * the canonical version of the specified path after ".." and "." elements
     * are resolved out.  If the specified path attempts to go outside the
     * boundaries of the current context (i.e. too many ".." path elements
     * are present), return <code>null</code> instead.
     *
     * @param path Path to be normalized
     * 拷贝方法 TODO 源码未读
     */
    protected String normalize(String path) {
        if (path == null)
            return null;
        // Create a place for the normalized path
        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);

    }
}