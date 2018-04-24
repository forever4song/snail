package cn.foreversong.snail.sf;

import cn.foreversong.snail.sf.helper.*;
import cn.foreversong.snail.sf.util.ClassUtil;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 22:00
 * Description: 启动类
 */
public final class BootStrap {
    /**
     * 初始化
     */
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class,
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}