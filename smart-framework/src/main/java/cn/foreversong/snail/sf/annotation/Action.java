package cn.foreversong.snail.sf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-15
 * Time: 上午9:00
 * Description: 方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     *  请求类型与路径
     * @return
     */
    String value();
}
