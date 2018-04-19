package cn.foreversong.snail.sf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/19
 * Time: 8:01
 * Description: 编码解码工具类
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * URL 编码
     * @param source 要编码的URL
     * @return 编码后的URL
     */
    public static String encodeURL(String source){
        String target;
        try{
            target = URLEncoder.encode(source,"UTF-8");
        } catch (Exception e) {
            LOGGER.error("encode url failure", e);
            throw new RuntimeException();
        }
        return target;
    }

    /**
     * URL 解码
     * @param source  要解码的URL
     * @return 解码后的URL
     */
    public static String decodeURL(String source){
        String target;
        try{
            target = URLDecoder.decode(source,"UTF-8");
        }catch (Exception e){
            LOGGER.error("decode url failure",e);
            throw new RuntimeException();
        }
        return target;
    }
}