package cn.eternalsong.snail.htw.ex03.startup;

import cn.eternalsong.snail.htw.ex03.connector.http.HttpConnector;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 12:32
 * Description: 应用程序启动类
 */
public final class Bootstrap {
    /**
     * 主进程，用于启动服务进程
     * @param args
     */
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}