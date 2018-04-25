package threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/25
 * Time: 14:46
 * Description: 自定义Thread
 */
public class MyThreadLocalLocal<T> {
    private Map<Thread,T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    /**
     * setter
     * @param value 值
     */
    public void set(T value){
        container.put(Thread.currentThread(), value);
    }

    /**
     * getter
     * @return 值
     */
    public T get(){
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if(value == null && container.containsKey(thread)){
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    /**
     * 移除值
     */
    public void remove(){
        container.remove(Thread.currentThread());
    }

    /**
     * 初始化值
     * @return 初始化的值
     */
    protected T initialValue() {
        return null;
    }
}