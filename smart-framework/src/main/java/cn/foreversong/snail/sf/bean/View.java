package cn.foreversong.snail.sf.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/19
 * Time: 7:37
 * Description: 视图类
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        this.model = new HashMap<String,Object>();
    }

    /**
     * 添加模型数据
     * @param key 关键字
     * @param value 值
     * @return 视图对象
     */
    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}