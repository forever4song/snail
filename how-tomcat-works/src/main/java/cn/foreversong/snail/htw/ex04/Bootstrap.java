package cn.foreversong.snail.htw.ex04;


import org.apache.catalina.connector.http.HttpConnector;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/13
 * Time: 11:39
 * Description: 启动器
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        SimpleContainer container = new SimpleContainer();
        connector.setContainer(container);
        try {
            /*
             调用HttpConnector的open方法，通过Socket工厂类获取一个ServerSocket并返回
              */
            connector.initialize();
            /*
            while(this.curProcessors < this.minProcessors && (this.maxProcessors <= 0 || this.curProcessors < this.maxProcessors)) {
                HttpProcessor processor = this.newProcessor();
                this.recycle(processor);
            }
            当前线程数小于最小线程数 或者 无可用线程且线程未达到最大线程时，创建一个新线程

            newProcessor() 最终到达  this.process(socket); 调用构造方法
                   this.parseConnection
                   this.parseRequest
                   this.response.setHeader
                   this.connector.getContainer().invoke -- 调用invoke方法进行响应处理
                   如果响应结束标志为是
                   关闭Request和Reponse中的资源
             */

            connector.start();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}