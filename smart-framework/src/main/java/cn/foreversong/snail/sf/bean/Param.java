package cn.foreversong.snail.sf.bean;

import cn.foreversong.snail.sf.util.CastUtil;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 22:07
 * Description: 请求参数对象
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取 Long 类型参数
     * @param name 参数名称
     * @return 参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}