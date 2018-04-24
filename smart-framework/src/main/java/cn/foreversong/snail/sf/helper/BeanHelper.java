package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/17
 * Time: 8:48
 * Description: Bean 助手类
 */
public final class BeanHelper {

    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
        beanClassSet.forEach((beanClass)->{
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        });
    }

    /**
     * 获取 Bean 集合
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取 Bean 实例
     * @param cls 类对象
     * @param <T> 返回类类型
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can't get bean by class : "+cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

    /**
     * 设置 Bean 实例
     * @param cls 类
     * @param obj 实例
     */
    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}