package cn.foreversong.snail.sf.util;

import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:20
 * Description: 集合工具类
 */
public final class CollectionUtil {
    /**
     * 判断Collection是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtil.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断Map是否为空
     *
     * @param map Map 对象
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}