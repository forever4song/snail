package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.annotation.Action;
import cn.foreversong.snail.sf.bean.Handler;
import cn.foreversong.snail.sf.bean.Request;
import cn.foreversong.snail.sf.util.ArrayUtil;
import cn.foreversong.snail.sf.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:49
 * Description: 控制器助手类
 */
public final class ControllerHelper {
    /**
     * 用于存放请求与处理器的映射关系Action Map
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static{
        // 获取所有的Controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass : controllerClassSet){
                Method[] methods = controllerClass.getMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    for (Method method : methods){
                        if(method.isAnnotationPresent(Action.class)){
                            // 从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            // 验证URL映射规则
                            if(mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    // 获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    // 初始化Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requstMethod 请求方法
     * @param requestPath 请求路径
     * @return
     */
    public static Handler getHandler(String requstMethod,String requestPath){
        Request request = new Request(requstMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}