package cn.foreversong.snail.sf.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/18
 * Time: 21:41
 * Description: Request 封装
 */
public class Request {

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
}