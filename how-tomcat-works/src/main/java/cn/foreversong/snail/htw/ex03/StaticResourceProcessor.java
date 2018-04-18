package cn.foreversong.snail.htw.ex03;

import cn.foreversong.snail.htw.ex03.connector.http.HttpRequest;
import cn.foreversong.snail.htw.ex03.connector.http.HttpResponse;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/9
 * Time: 17:57
 * Description: 静态资源处理器
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}