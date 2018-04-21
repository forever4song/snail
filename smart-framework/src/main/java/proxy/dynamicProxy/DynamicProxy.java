package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/21
 * Time: 21:14
 * Description: 动态代理类
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }


    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("start time : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }

    private void after() {
        System.out.println("end   time : " + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
    }
}