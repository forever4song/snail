package cn.foreversong.snail.sf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/16
 * Time: 20:27
 * Description: 反射工具类
 */
public final class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     * @param cls 需要实例化的类对象
     * @return
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try{
            instance = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 执行方法
     * @param obj   类
     * @param method    方法
     * @param args  参数
     * @return
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result;
        try{
            method.setAccessible(true); // 修改方法为可以访问
            result = method.invoke(obj,args);
        }catch (Exception e){
            LOGGER.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * 设置成员变量的值
     * @param obj  类
     * @param field 字段
     * @param value 值
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}