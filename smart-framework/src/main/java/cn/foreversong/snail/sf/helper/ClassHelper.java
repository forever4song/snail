package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.annotation.Controller;
import cn.foreversong.snail.sf.annotation.Service;
import cn.foreversong.snail.sf.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/15
 * Time: 9:18
 * Description: 类操作助手类
 */
public final class ClassHelper {


    /**
     * 定义类集合，用于存放所有加载的类
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 返回所有类的集合
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包下所有的 Service 类
     */
    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Service.class));
            classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包下所有的 Controller 类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if(cls.isAnnotationPresent(Controller.class));
            classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包下所有的Bean类(包括: Servlce、Controller等)
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());
        return beanClassSet;
    }

}