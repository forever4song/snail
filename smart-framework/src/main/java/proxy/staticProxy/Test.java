package proxy.staticProxy;

import proxy.ForeverSong;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:07
 * Description: 测试类
 */
public class Test {
    public static void main(String[] args) {
        ForeverSongProxy foreverSongProxy = new ForeverSongProxy(new ForeverSong());
        foreverSongProxy.dance("舞蹈");
        foreverSongProxy.sing("一首歌");
    }
}