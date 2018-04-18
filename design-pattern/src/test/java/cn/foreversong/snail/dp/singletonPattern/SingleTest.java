package cn.foreversong.snail.dp.singletonPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/1
 * Time: 7:22
 * Description: 单例测试类
 */
public class SingleTest {

    public static void main(String[] args) {
        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
//        Singleton obj1 = Singleton.getInstance();
//        Singleton obj2 = Singleton.getInstance();
//
//        //显示消息
//        obj1.showMessage();
//        System.out.println("obj1["+obj1+"] == obj2["+obj2+"] : "+(obj1 == obj2));

        Singleton6 ins1 = Singleton6.INSTANCE;
        Singleton6 ins2 = Singleton6.INSTANCE;
        ins1.whateverMethod();
        System.out.println("ins1["+ins1+"] == ins2["+ins2+"] : "+(ins1 == ins2));
    }
}