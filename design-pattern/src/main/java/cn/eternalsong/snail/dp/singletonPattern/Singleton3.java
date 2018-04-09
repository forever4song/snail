package cn.eternalsong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:31
 * Description: 单例对象，饿汉式，线程安全，无Lazy加载，浪费内存
 */
public class Singleton3 {
    // 类装载时对象就会实例化，浪费内存
    private static Singleton3 instance = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance;
    }
}