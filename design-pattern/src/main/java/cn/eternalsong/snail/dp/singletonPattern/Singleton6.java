package cn.eternalsong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:47
 * Description: 单例对象，枚举，线程安全，无Laz加载
 */
public enum Singleton6 {
    //这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
    INSTANCE;
    public void whateverMethod() {
        System.out.println("enum signle instance");
    }
}