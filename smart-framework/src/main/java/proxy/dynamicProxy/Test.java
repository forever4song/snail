package proxy.dynamicProxy;

import proxy.Dance;
import proxy.ForeverSong;
import proxy.Sing;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:27
 * Description: 测试类
 */
public class Test {
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new ForeverSong());
        Sing sing = dynamicProxy.getProxy(); // 调用
        sing.sing(" 一首歌");
        Dance dance = dynamicProxy.getProxy();
        dance.dance("一个舞蹈");
    }
}