package cn.foreversong.snail.sf.proxy;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/23
 * Time: 22:22
 * Description: 代理接口
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}