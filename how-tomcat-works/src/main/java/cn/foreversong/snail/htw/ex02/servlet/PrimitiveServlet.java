package cn.foreversong.snail.htw.ex02.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-1
 * Time: 上午8:09
 * Description: 自定义 servlet
 */
public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("PrimitiveServlet init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     *  自定义Servlet的service方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("PrimitiveServlet start");
        PrintWriter out = response.getWriter();
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("PrimitiveServlet destroy");
    }
}
