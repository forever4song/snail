package cn.foreversong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:28
 * Description: 单例对象，懒汉式，线程安全，Lazy加载，性能低
 */
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    // 加锁 synchronized  保证单例，影响效率
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
