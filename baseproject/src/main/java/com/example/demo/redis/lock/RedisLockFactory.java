package com.example.demo.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 虎哥
 */
@Component
public class RedisLockFactory {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public RedisLock getReentrantLock(String key){
        return new ReentrantRedisLock(redisTemplate, key);
    }
}