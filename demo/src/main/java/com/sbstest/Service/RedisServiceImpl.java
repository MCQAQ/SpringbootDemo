package com.sbstest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by 21510 on 2021/7/22.
 */

@Service
public class RedisServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {

            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
