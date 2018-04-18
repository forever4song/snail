package cn.foreversong.snail.sf.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:14
 * Description: 数组工具类
 */
public final class ArrayUtil {

    /**
     * 判断数组是否非空
     * @param array 数组
     * @return 是否非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断数组是否为空
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}