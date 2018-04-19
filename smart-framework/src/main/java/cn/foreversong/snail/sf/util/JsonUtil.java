package cn.foreversong.snail.sf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/19
 * Time: 8:07
 * Description: Json 工具类
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将 Pojo类 转为 JSON
     * @param obj Pojo类
     * @param <T> 返回类型
     * @return JSON
     */
    public static <T> String toJson(T obj){
        String json;
        try{
            json = OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            LOGGER.error("convert POJO to JSON failure",e);
            throw new RuntimeException();
        }
        return json;
    }

    /**
     * 将JSON转换为Pojo类
     * @param json JSON
     * @param type 类类型
     * @param <T> 返回类型
     * @return Pojo类
     */
    public static <T> T fromJson(String json,Class<T> type){
        T pojo;
        try{
            pojo = OBJECT_MAPPER.readValue(json,type);
        }catch (Exception e){
            LOGGER.error("convert JSON to POJO failure",e);
            throw new RuntimeException();
        }
        return pojo;
    }
}