package cn.foreversong.snail.htw.ex03.connector.http;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/3
 * Time: 13:02
 * Description: 常量类
 */
public class Constants {
    /**
     * WEB_ROOT 静态资源根路径
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") +
            File.separator + "how-tomcat-works" +
            File.separator + "src" +
            File.separator + "main" +
            File.separator + "resources";
    /**
     * CLASS_ROOT 二进制文件路径
     */
    public static final String CLASS_ROOT = System.getProperty("user.dir") +
            File.separator + "how-tomcat-works" +
            File.separator + "target" +
            File.separator + "classes";

    public static final String Package = "cn.eternalsong.snail.htw.ex03.connector.http";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
    public static final int PROCESSOR_IDLE = 0;
    public static final int PROCESSOR_ACTIVE = 1;
}