package cn.foreversong.snail.htw.ex04;

import org.apache.catalina.*;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/13
 * Time: 11:40
 * Description: 容器
 */
public class SimpleContainer implements Container {

    /**
     * WEB_ROOT 静态资源根路径
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") +
            File.separator + "how-tomcat-works" +
            File.separator + "src" +
            File.separator + "main" +
            File.separator + "resources";

    public String getInfo() {
        return null;
    }

    public Loader getLoader() {
        return null;
    }

    public void setLoader(Loader loader) {

    }

    public Logger getLogger() {
        return null;
    }

    public void setLogger(Logger logger) {

    }

    public Manager getManager() {
        return null;
    }

    public void setManager(Manager manager) {

    }

    public Cluster getCluster() {
        return null;
    }

    public void setCluster(Cluster cluster) {

    }

    public String getName() {
        return null;
    }

    public void setName(String s) {

    }

    public Container getParent() {
        return null;
    }

    public void setParent(Container container) {

    }

    public ClassLoader getParentClassLoader() {
        return null;
    }

    public void setParentClassLoader(ClassLoader classLoader) {

    }

    public Realm getRealm() {
        return null;
    }

    public void setRealm(Realm realm) {

    }

    public DirContext getResources() {
        return null;
    }

    public void setResources(DirContext dirContext) {

    }

    public void addChild(Container container) {

    }

    public void addContainerListener(ContainerListener containerListener) {

    }

    public void addMapper(Mapper mapper) {

    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

    }

    public Container findChild(String s) {
        return null;
    }

    public Container[] findChildren() {
        return new Container[0];
    }

    public ContainerListener[] findContainerListeners() {
        return new ContainerListener[0];
    }

    public Mapper findMapper(String s) {
        return null;
    }

    public Mapper[] findMappers() {
        return new Mapper[0];
    }

    public void invoke(Request request, Response response) throws IOException, ServletException {
        String servletName = ((HttpServletRequest)request).getRequestURI();
        servletName = servletName.substring(servletName.lastIndexOf("/")+1);
        URLClassLoader loader = null;
        try{
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(WEB_ROOT);
            String repository = (new URL("file",null,classPath.getCanonicalPath()+ File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch (IOException e){
            System.out.println(e.toString());
        }
        Class myClass = null;
        try{
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        try{
            servlet = (Servlet) myClass.newInstance();
            servlet.service((HttpServletRequest)request,(HttpServletResponse)response);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public Container map(Request request, boolean b) {
        return null;
    }

    public void removeChild(Container container) {

    }

    public void removeContainerListener(ContainerListener containerListener) {

    }

    public void removeMapper(Mapper mapper) {

    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

    }
}