package com.yogo.fm.common.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-16
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 定义缓存数据 key 生成策略的bean
     * 包名+类名+方法名+所有参数
     *
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(".");
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 针对model的find和update操作，将查询或者更新的model 的key
     * 设置为 包名+service类名+model类名+id
     *
     * @return
     */
    @Bean
    public KeyGenerator modelKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(".");
            Class<?> clazz = method.getReturnType();
            sb.append(clazz.getSimpleName());
            sb.append(".");
            try {
                for (Object obj : params) {
                    if (obj instanceof Long) {
                        sb.append(obj.toString());
                    } else {
                        Class<?> superclass = obj.getClass().getSuperclass();
                        Field field = superclass.getDeclaredField("id");
                        if (field != null) {
                            field.setAccessible(true);
                            sb.append(field.get(obj).toString());
                        }
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            return sb.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        Map<String, Long> map = new HashMap<>(2);
        //token对应登录的账号，过期时间2小时
        map.put(RedisKeys.TOKEN_ACCOUNT, 2L * 60 * 60);
        //普通缓存，5分钟过期
        map.put(RedisKeys.MODEL, 5L * 60);
        cacheManager.setExpires(map);
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        //使用jdk自带的序列化
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }
}
