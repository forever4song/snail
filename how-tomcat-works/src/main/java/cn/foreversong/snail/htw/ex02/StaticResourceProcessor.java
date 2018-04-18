package cn.foreversong.snail.htw.ex02;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-1
 * Time: 上午8:54
 * Description: 静态资源处理类
 */
public class StaticResourceProcessor {
    /**
     *  调用 Response 方法实现静态资源处理
     * @param request
     * @param response
     */
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
