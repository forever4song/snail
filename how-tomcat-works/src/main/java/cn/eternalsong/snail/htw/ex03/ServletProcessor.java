package cn.eternalsong.snail.htw.ex03;

import cn.eternalsong.snail.htw.ex02.Constants;
import cn.eternalsong.snail.htw.ex03.connector.http.HttpRequest;
import cn.eternalsong.snail.htw.ex03.connector.http.HttpRequestFacade;
import cn.eternalsong.snail.htw.ex03.connector.http.HttpResponse;
import cn.eternalsong.snail.htw.ex03.connector.http.HttpResponseFacade;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/7
 * Time: 9:26
 * Description: Servlet 处理器
 */
public class ServletProcessor {
    public void process(HttpRequest request, HttpResponse response){
        String uri = request.getRequestURI();
        String servletName = uri.substring(uri.indexOf("/")+1);
        URLClassLoader loader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath =  new File(Constants.WEB_ROOT);
            String repository = (new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch(IOException e){
            System.out.println(e.toString() );
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            HttpRequestFacade requestFacade = new HttpRequestFacade(request);
            HttpResponseFacade responseFacade = new HttpResponseFacade(response);
            servlet.service(requestFacade, responseFacade);
            ((HttpResponse) response).finishResponse();
        }catch (Exception e){
            System.out.println(e.toString());
        }catch (Throwable e){
            System.out.println(e.toString());
        }

    }
}