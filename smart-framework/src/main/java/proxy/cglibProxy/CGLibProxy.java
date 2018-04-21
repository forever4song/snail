package proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:40
 * Description: CGLib 动态代理类
 */
public class CGLibProxy implements MethodInterceptor {
    /*
    需要引入CGLib依赖
    maven坐标：
        <!-- CGLib 动态代理 -->
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.1</version>
        </dependency>
    */
    private volatile static CGLibProxy instance;
    private Object obj;

    private CGLibProxy(){}

    /**
     * 单例模式获取对象
     * @return CGLibProxy对象
     */
    public static CGLibProxy getInstance(){
        if(instance == null){
            synchronized (CGLibProxy.class){
                if(instance == null)
                    instance = new CGLibProxy();
            }
        }
        return instance;
    }

    /**
     * 获取代理类
     * @param clazz 代理类类对象
     * @param <T> 代理类类型
     * @return 代理类
     */
      public <T> T getProxy(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);// 设置代理目标
        enhancer.setCallback(this);// 设置回调
        enhancer.setClassLoader(clazz.getClass().getClassLoader());
        return (T)enhancer.create();
    }

    /**
     *  调用方法
     */
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            // 前置通知
            before();
            result = methodProxy.invokeSuper(obj, args);
            // 后置通知
            after();
        } catch (Exception e) {
            exception();
        }finally{
            beforeReturning();
        }
        return result;
    }

    private void before() {
        System.out.println("start time : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    private void after() {
        System.out.println("end   time : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
    private void exception() {
        System.out.println("method invoke exception");
    }
    private void beforeReturning() {
        System.out.println("before returning");
    }
}