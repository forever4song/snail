package cn.foreversong.snail.dp.proxyPattern.cglibProxy;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 22:16
 * Description: 被代理类
 */
public class BusiSrv {
    public void service() throws InterruptedException {
        System.out.println("执行业务处理");
        Thread.sleep(1000);
    }
}