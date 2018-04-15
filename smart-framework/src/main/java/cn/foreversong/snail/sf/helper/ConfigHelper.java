package cn.foreversong.snail.sf.helper;

import cn.foreversong.snail.sf.ConfigConstant;
import cn.foreversong.snail.sf.util.PropsUtil;


import java.util.Properties;

import static cn.foreversong.snail.sf.ConfigConstant.*;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-14
 * Time: 下午5:08
 * Description: 属性文件助手类
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取 JDBC 驱动
     *
     * @return
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, JDBC_DRIVER);
    }

    /**
     * 获取 JDBC URL
     *
     * @return
     */
    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, JDBC_URL);
    }

    /**
     * 获取 JDBC 用户名
     *
     * @return
     */
    public static String getJdbcUsername() {
        return PropsUtil.getString(CONFIG_PROPS, JDBC_USERNAME);
    }

    /**
     * 获取 JDBC 用密码
     *
     * @return
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     *
     * @return
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, APP_BASE_PACKAGE);
    }

    /**
     * 获取jsp路径
     *
     * @return
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, APP_JSP_PATH);
    }

    /**
     * 获取静态资源路径
     *
     * @return
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, APP_ASSET_PATH);
    }

    /**
     * 获取应用文件上传限制
     * @return
     */
    public static int getAppUploadLimit(){
        return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT,10);
    }
}
