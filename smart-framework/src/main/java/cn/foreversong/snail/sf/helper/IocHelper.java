package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.annotation.Inject;
import cn.foreversong.snail.sf.util.ArrayUtil;
import cn.foreversong.snail.sf.util.CollectionUtil;
import cn.foreversong.snail.sf.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:12
 * Description: 依赖注入助手类
 */
public final class IocHelper {
    static{
        // 获取所有的 Bean类 与 Bean 实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            beanMap.forEach((beanClass,beanInstance)->{
                Field[] beanFields = beanClass.getDeclaredFields(); // 获取Bean类定义的所有变量
                if(ArrayUtil.isNotEmpty(beanFields)){
                    // 遍历Bean Field  判断当前bean field是否带有Inject注解 有注解进行注入
                    for (Field beanField : beanFields) {
                        if(beanField.isAnnotationPresent(Inject.class)){
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if(beanFieldInstance != null){
                                // 通过反射初始化BeanField的值
                                ReflectionUtil.setField(beanInstance,beanField,beanInstance);
                            }else{
                                // 未找到注解对应的类
                            }
                        }
                    }
                }
            });
        }
    }
}