package proxy.cglibProxy;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:39
 * Description: 测试类
 */
public class Test {
    /*
    1、CGLib代理创建代理的速度较慢，但是创建之后运行的速度非常快
    2、JDK动态代理正好相反
    故：
        系统初始化时可以使用CGLib去创建代理，并将其放入IOC容器中供以后使用。
     */
    public static void main(String[] args) throws InterruptedException {
//        ForeverSong foreverSong = CGLibProxy.getInstance().getProxy(ForeverSong.class);
//        foreverSong.sing("一首歌");

        BusiSrv busiSrv = CGLibProxy.getInstance().getProxy(BusiSrv.class);
        busiSrv.service();
    }
}