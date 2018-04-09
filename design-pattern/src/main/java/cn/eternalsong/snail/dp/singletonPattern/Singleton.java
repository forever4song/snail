package cn.eternalsong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:21
 * Description: 单例对象，懒汉式，线程不安全，Lazy加载
 */
public class Singleton {
    //创建 SingleObject 的一个对象
    private static Singleton instance = new Singleton();

    //让构造函数为 private，这样该类就不会被实例化
    private Singleton(){}

    //获取唯一可用的对象
    public static Singleton getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}