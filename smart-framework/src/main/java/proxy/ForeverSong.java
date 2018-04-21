package proxy;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:01
 * Description: 实现类
 */
public class ForeverSong implements Sing, Dance {

    public void sing(String name) {
        System.out.println("Sing " + name);
    }

    public void dance(String name) {
        System.out.println("Dance " + name);
    }
}