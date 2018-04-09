package cn.eternalsong.snail.dp.observerPattern;

import cn.eternalsong.snail.dp.observerPattern.BinaryObserver;
import cn.eternalsong.snail.dp.observerPattern.HexaObserver;
import cn.eternalsong.snail.dp.observerPattern.OctalObserver;
import cn.eternalsong.snail.dp.observerPattern.Subject;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-3-31
 * Time: 下午4:55
 * Description: 观察者模式测试类
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
