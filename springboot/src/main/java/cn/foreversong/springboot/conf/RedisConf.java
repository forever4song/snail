package cn.foreversong.springboot.conf;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.jcache.config.JCacheConfigurerSupport;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-26
 * Time: 下午11:12
 * Description: Redis 配置类
 */
@Configuration  // 表明这是一个配置类
@EnableCaching  // 开启缓存
public class RedisConf extends JCacheConfigurerSupport {

    // 自定义缓存生成key策略
    @Override
    public KeyGenerator keyGenerator() {
        return (Object o, Method method, Object... objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj:objects) {
                sb.append(obj.toString());
            }
            System.out.println("redis cache generate key : "+sb.toString());
            return sb.toString();
        };
    }
}
