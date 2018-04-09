package cn.eternalsong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:40
 * Description: 单例对象，登记式，线程安全，Lazy加载
 */
public class Singleton5 {
    // 优化了Singleton2，使用静态内部类实现了Lazy加载
    private static class SingleHolder{
        private static final Singleton5 INSTANCE  = new Singleton5();
    }
    private Singleton5(){}

    public static Singleton5 getInstance(){
        return SingleHolder.INSTANCE;
    }
}