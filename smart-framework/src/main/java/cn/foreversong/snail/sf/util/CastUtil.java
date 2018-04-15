package cn.foreversong.snail.sf.util;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-14
 * Time: 下午5:12
 * Description: 类型转换工具类
 */
public final class CastUtil {
    /**
     * 转为String型
     *
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj, "");
    }

    /**
     * 转为String型(提供默认值)
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    /**
     * 转为Double型
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj, double defaultValue) {
        double dValue = defaultValue;
        if (obj != null) {
            String sValue = castString(obj);
            if (StringUtil.isNotEmpty(sValue)) {
                try {
                    defaultValue = Double.parseDouble(sValue);
                } catch (NumberFormatException e) {
                    dValue = defaultValue;
                }
            }
        }
        return dValue;
    }

    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为long型(提供默认值)
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj, long defaultValue) {
        long lValue = defaultValue;
        if (obj != null) {
            String sValue = castString(obj);
            if (StringUtil.isNotEmpty(sValue)) {
                try {
                    lValue = Long.parseLong(sValue);
                } catch (NumberFormatException e) {
                    lValue = defaultValue;
                }
            }
        }
        return lValue;

    }

    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    public static int castInt(Object obj, int defaultValue) {
        int iValue = defaultValue;
        if (obj != null) {
            String sValue = castString(obj);
            if (StringUtil.isNotEmpty(sValue)) {
                try {
                    iValue = Integer.parseInt(sValue);
                } catch (NumberFormatException e) {
                    iValue = defaultValue;
                }
            }
        }
        return iValue;
    }

    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean bValue = defaultValue;
        if (obj != null) {
            bValue = Boolean.parseBoolean(castString(obj));
        }
        return bValue;
    }
}
