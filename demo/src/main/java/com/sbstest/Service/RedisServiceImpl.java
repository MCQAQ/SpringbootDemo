package com.sbstest.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
            RedisSerializer stringSerializer = new StringRedisSerializer();
            redisTemplate.setKeySerializer(stringSerializer);
            redisTemplate.setValueSerializer(stringSerializer);
            redisTemplate.setHashKeySerializer(stringSerializer);
            redisTemplate.setHashValueSerializer(stringSerializer);
            this.redisTemplate = redisTemplate;
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
