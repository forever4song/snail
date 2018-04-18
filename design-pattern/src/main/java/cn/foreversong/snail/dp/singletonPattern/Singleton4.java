package cn.foreversong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:34
 * Description: 单例对象，双重锁验锁（DCL，即 double-checked locking）,线程安全，Lazy加载，性能较高，实现复杂
 */
public class Singleton4 {
    private volatile static Singleton4 singleton;
    private Singleton4 (){}
    public static Singleton4 getSingleton() {
        // 当对象没有加载时，拿锁进行对象实例化保证单例。对象加载时，直接返回，性能较好
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}