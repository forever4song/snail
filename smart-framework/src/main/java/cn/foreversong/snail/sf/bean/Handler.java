package cn.foreversong.snail.sf.bean;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:47
 * Description: Action 封装
 */
public class Handler {
    /**
     * Controller 类
     */
    private Class<?> controllerClass ;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}