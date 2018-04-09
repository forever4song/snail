package cn.eternalsong.snail.htw.ex03.connector.http;

import cn.eternalsong.snail.htw.ex03.connector.RequestStream;
import org.apache.catalina.util.Enumerator;
import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 12:47
 * Description: Http 请求
 */
public class HttpRequest implements HttpServletRequest {

    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream input;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;
    private InputStream in;

    /**
     * The request attributes for this request.
     */
    protected HashMap attributes = new HashMap();
    /**
     * The authorization credentials sent with this Request.
     */
    protected String authorization = null;
    /**
     * The context path for this request.
     */
    protected String contextPath = "";
    /**
     * The set of cookies associated with this Request.
     */
    protected ArrayList cookies = new ArrayList();
    /**
     * An empty collection to use for returning empty Enumerations.  Do not
     * add any elements to this collection!
     */
    protected static ArrayList empty = new ArrayList();
    /**
     * The set of SimpleDateFormat formats to use in getDateHeader().
     */
    protected SimpleDateFormat formats[] = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
    };
    /**
     * The HTTP headers associated with this Request, keyed by name.  The
     * values are ArrayLists of the corresponding header values.
     */
    protected HashMap headers = new HashMap();
    /**
     * The parsed parameters for this request.  This is populated only if
     * parameter information is requested via one of the
     * <code>getParameter()</code> family of method calls.  The key is the
     * parameter name, while the value is a String array of values for this
     * parameter.
     * <p>
     * <strong>IMPLEMENTATION NOTE</strong> - Once the parameters for a
     * particular request are parsed and stored here, they are not modified.
     * Therefore, application level access to the parameters need not be
     * synchronized.
     */
    protected ParameterMap parameters = null;

    /**
     * Have the parameters for this request been parsed yet?
     */
    protected boolean parsed = false;
    protected String pathInfo = null;

    /**
     * The reader that has been returned by <code>getReader</code>, if any.
     */
    protected BufferedReader reader = null;

    /**
     * The ServletInputStream that has been returned by
     * <code>getInputStream()</code>, if any.
     */
    protected ServletInputStream stream = null;

    /**
     * 添加请求头属性
     * 首先判断请求头属性是否存在，不存在时初始化。实现按需加载
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList vales = (ArrayList) headers.get(name);
            if (vales == null) {
                vales = new ArrayList();
                headers.put(name, value);
            }
            vales.add(value);
        }
    }

    /**
     * 解析请求参数
     * 1. 首先判断是否解析过
     * 2. 依次解析 编码参数、请求参数、文本类型
     * 3. 调用RequestUtil的parseParameters 解析请求参数
     * PS： ParameterMap 继承了HashMap，对put，get进行加标记判断,解析后的参数集合无法修改
     */
    protected void parseParameters() {
        if (parsed)
            return;
        ParameterMap results = parameters;
        if (results == null)
            results = new ParameterMap();
        results.setLocked(false);
        String encoding = getCharacterEncoding();
        if (encoding == null)
            encoding = "ISO-8859-1";
        // Parse any parameters specified in the query string
        String queryString = getQueryString();
        try {
            RequestUtil.parseParameters(results, queryString, encoding);    // TODO 源码未读
        } catch (UnsupportedEncodingException e) {
            ;
        }
        // Parse any parameters specified in the input stream
        String contentType = getContentType();
        if (contentType == null)
            contentType = "";
        int semicolon = contentType.indexOf(";");
        if (semicolon > 0) {
            contentType = contextPath.substring(0, semicolon).trim();
        } else {
            contentType = contentType.trim();
        }
        // 解析用户POST提交的数据
        if ("POST".equals(getMethod()) && getContentLength() > 0
                && "application/x-www-form-urlencoded".equals(contentType)) {
            try {
                int max = getContentLength();
                int len = 0;
                byte buf[] = new byte[getContentLength()];
                ServletInputStream is = getInputStream();
                while (len < max) {
                    int next = is.read(buf, len, max - len);
                    if (next < 0) {
                        break;
                    }
                    len += next;
                }
                is.close();
                if (len < max) {
                    throw new RuntimeException("Content length mismatch");
                }
                RequestUtil.parseParameters(results, buf, encoding);  //TODO 源码未读
            } catch (UnsupportedEncodingException ue) {
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Store the final results
            results.setLocked(true);
            parsed = true;
            parameters = results;
        }
    }

    /**
     * 增加 Cookie 参数
     *
     * @param cookie
     */
    public void addCookie(Cookie cookie) {
        synchronized (cookies) {
            cookies.add(cookie);
        }
    }

    /**
     * 创建ServletInputStream输入流
     *
     * @return
     * @throws IOException
     */
    public ServletInputStream createInputStream() throws IOException {
        return (new RequestStream(this));
    }

    /**
     * 返回 InputStream
     *
     * @return
     */
    public InputStream getStream() {
        return in;
    }

    public HttpRequest(InputStream in) {
        this.in = in;
    }

    /**
     * 设置返回内容长度
     *
     * @param length
     */
    public void setContentLength(int length) {
        this.contentLength = length;
    }

    /**
     * 设置文本内容格式
     *
     * @param type
     */
    public void setContentType(String type) {
        this.contentType = type;
    }

    /**
     * 设置Ip地址
     *
     * @param inetAddress
     */
    public void setInet(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    /**
     * 设置应用上下文路径
     *
     * @param path
     */
    public void setContextPath(String path) {
        if (path == null) {
            this.contextPath = "";
        } else {
            this.contextPath = path;
        }
    }

    /**
     * 设置请求方法
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 设置路径信息
     *
     * @param path
     */
    public void setPathInfo(String path) {
        this.pathInfo = path;
    }

    /**
     * 设置协议信息
     *
     * @param protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
        ;
    }

    /**
     * 设置查询字符串
     *
     * @param queryString
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * 设置请求URI
     *
     * @param requestURI
     */
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * 设置服务器名称
     *
     * @param name
     */
    public void setServerName(String name) {
        this.serverName = name;
    }

    /**
     * 设置服务端口
     *
     * @param port
     */
    public void setServerPort(int port) {
        this.serverPort = port;
    }

    /**
     * 设置Socket对象
     *
     * @param socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 设置是否记录Cookie和Session
     *
     * @param requestedSessionCookie
     */
    public void setRequestedSessionCookie(boolean requestedSessionCookie) {
        this.requestedSessionCookie = requestedSessionCookie;
    }

    /**
     * 设置请求session
     *
     * @param requestedSessionId
     */
    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    /**
     * 设置标志(会话标识是否在URI中)
     *
     * @param flag
     */
    public void setRequestedSessionURL(boolean flag) {
        this.requestedSessionURL = flag;
    }

    /* implementation of the HttpServletRequest*/
    public Object getAttribute(String name) {
        synchronized (attributes) {
            return (attributes.get(name));
        }
    }

    public Enumeration getAttributeNames() {
        synchronized (attributes) {
            return (new Enumerator(attributes.keySet()));
        }
    }

    public String getAuthType() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public int getContentLength() {
        return contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContextPath() {
        return contextPath;
    }

    public Cookie[] getCookies() {
        synchronized (cookies) {
            if (cookies.size() < 1)
                return (null);
            Cookie results[] = new Cookie[cookies.size()];
            return ((Cookie[]) cookies.toArray(results));
        }
    }

    public long getDateHeader(String name) {
        String value = getHeader(name);
        if (value == null)
            return (-1L);

        // Work around a bug in SimpleDateFormat in pre-JDK1.2b4
        // (Bug Parade bug #4106807)
        value += " ";

        // Attempt to convert the date header in a variety of formats
        for (int i = 0; i < formats.length; i++) {
            try {
                Date date = formats[i].parse(value);
                return (date.getTime());
            } catch (ParseException e) {
                ;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public String getHeader(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values != null)
                return ((String) values.get(0));
            else
                return null;
        }
    }

    public Enumeration getHeaderNames() {
        synchronized (headers) {
            return (new Enumerator(headers.keySet()));
        }
    }

    public Enumeration getHeaders(String name) {
        name = name.toLowerCase();
        synchronized (headers) {
            ArrayList values = (ArrayList) headers.get(name);
            if (values != null)
                return (new Enumerator(values));
            else
                return (new Enumerator(empty));
        }
    }

    public ServletInputStream getInputStream() throws IOException {
        if (reader != null)
            throw new IllegalStateException("getInputStream has been called");

        if (stream == null)
            stream = createInputStream();
        return (stream);
    }

    public int getIntHeader(String name) {
        String value = getHeader(name);
        if (value == null)
            return (-1);
        else
            return (Integer.parseInt(value));
    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration getLocales() {
        return null;
    }

    public String getMethod() {
        return method;
    }

    public String getParameter(String name) {
        parseParameters();
        String values[] = (String[]) parameters.get(name);
        if (values != null)
            return (values[0]);
        else
            return (null);
    }

    public Map getParameterMap() {
        parseParameters();
        return (this.parameters);
    }

    public Enumeration getParameterNames() {
        parseParameters();
        return (new Enumerator(parameters.keySet()));
    }


    public String[] getParameterValues(String name) {
        parseParameters();
        String values[] = (String[]) parameters.get(name);
        if (values != null)
            return (values);
        else
            return null;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getQueryString() {
        return queryString;
    }

    public BufferedReader getReader() throws IOException {
        if (stream != null)
            throw new IllegalStateException("getInputStream has been called.");
        if (reader == null) {
            String encoding = getCharacterEncoding();
            if (encoding == null)
                encoding = "ISO-8859-1";
            InputStreamReader isr =
                    new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return (reader);
    }

    public String getRealPath(String path) {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public String getRemoteUser() {
        return null;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public String getRequestedSessionId() {
        return null;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public HttpSession getSession() {
        return null;
    }

    public HttpSession getSession(boolean create) {
        return null;
    }

    public String getServletPath() {
        return null;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return isRequestedSessionIdFromURL();
    }

    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isSecure() {
        return false;
    }

    public boolean isUserInRole(String role) {
        return false;
    }

    public void removeAttribute(String attribute) {
    }

    public void setAttribute(String key, Object value) {
    }

    /**
     * Set the authorization credentials sent with this request.
     *
     * @param authorization The new authorization credentials
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
    }


    public int getRemotePort() {
        return 0;
    }


    public String getLocalName() {
        return null;
    }


    public String getLocalAddr() {
        return null;
    }


    public int getLocalPort() {
        return 0;
    }
}