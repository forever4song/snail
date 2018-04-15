package cn.foreversong.snail.sf.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-14
 * Time: 下午5:12
 * Description: 字符串工具类
 */
public final class StringUtil {
    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

    /**
     * 判断字符串是否为空
     * @param str 要判断的字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    public static String[] splitString(String str,String var){
        return str.split("var");
    }
}
