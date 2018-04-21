package proxy.cglibProxy;

import proxy.ForeverSong;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:39
 * Description: 测试类
 */
public class Test {
    public static void main(String[] args){
//        ForeverSong foreverSong = CGLibProxy.getInstance().getProxy(ForeverSong.class);
//        foreverSong.sing("一首歌");

        BusiSrv busiSrv = CGLibProxy.getInstance().getProxy(BusiSrv.class);
        busiSrv.service();
    }
}