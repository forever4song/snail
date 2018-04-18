package cn.foreversong.snail.htw.ex02;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-1
 * Time: 上午8:42
 * Description: Response 响应类
 */
public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream out;
    PrintWriter writer;

    public Response(OutputStream out){
        this.out = out;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    /**
     *  发送静态资源
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try{
            File file = new File(Constants.WEB_ROOT,request.getUri());
            fis = new FileInputStream(file);

            out.write(Constants.HEADER.getBytes());  // 输出响应报文头

            /*输出静态资源内容*/
            int ch = fis.read(bytes,0,BUFFER_SIZE);
            while(ch != -1){
                out.write(bytes);
                ch = fis.read(bytes,0,BUFFER_SIZE);
            }
        }catch (Exception e){
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            out.write(errorMessage.getBytes());
        }finally {
            if(fis != null){
                fis.close();
            }
        }
    }

    public PrintWriter getWriter() throws IOException {
        // 复写方法,执行out.println()时会自动刷新
        // autoflush is true, println() will flush,
        // but print() will not.
        writer = new PrintWriter(out, true);
        return writer;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {
        return null;
    }
}
