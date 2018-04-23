package proxy.staticProxy;

import proxy.Dance;
import proxy.ForeverSong;
import proxy.Sing;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:02
 * Description: 代理类
 */
public class ForeverSongProxy implements Sing, Dance {

    private ForeverSong foreverSong;

    public ForeverSongProxy(ForeverSong hello) {
        this.foreverSong = hello;
    }

    private void before(){

        System.out.println(String.format("Before : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
    }

    private void after(){
        System.out.println(String.format("After : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())));
    }

    public void dance(String name) {
        before();
        foreverSong.dance(name);
        after();
    }

    public void sing(String name) {
    before();
    foreverSong.sing(name);
    after();
    }
}