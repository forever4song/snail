package cn.eternalsong.snail.htw.ex03.connector.http;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 13:02
 * Description: 常量类
 */
public class Constants {
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator  + "webroot";
    public static final String Package = "cn.eternalsong.snail.htw.ex03.connector.http";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    public static final int PROCESSOR_IDLE = 0;
    public static final int PROCESSOR_ACTIVE = 1;
}