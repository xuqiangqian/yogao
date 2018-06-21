package com.yogo.fm.service.impl;

import com.yogo.fm.service.IRedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-22
 */
@Service
public class RedisHelper<T> implements IRedisHelper<T> {

    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisHelper(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getValue(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(String key, T data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
