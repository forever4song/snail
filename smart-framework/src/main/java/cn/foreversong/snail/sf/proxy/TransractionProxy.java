package cn.foreversong.snail.sf.proxy;

import cn.foreversong.snail.sf.annotation.Transaction;
import cn.foreversong.snail.sf.helper.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/26
 * Time: 18:34
 * Description: 事务代理
 */
public class TransractionProxy implements Proxy {

    private static final Logger log = LoggerFactory.getLogger(TransractionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = ThreadLocal.withInitial(() -> false);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag  = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if(!flag && method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try{
                DBHelper.beginTransraction();
                log.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DBHelper.commitTransraction();
                log.debug("close transaction");
            }catch (Throwable throwable){
                DBHelper.rollbackTransaction();
                log.error("rollback transaction");
                throw throwable;
            }finally {
                FLAG_HOLDER.remove();
            }
        }else{
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}