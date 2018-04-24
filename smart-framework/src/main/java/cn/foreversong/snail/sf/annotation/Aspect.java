package cn.foreversong.snail.sf.annotation;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/23
 * Time: 22:11
 * Description: 切面注解
 */
public @interface Aspect {

    /**
     * 注解
     */
    Class<? extends Annotation> value();
}