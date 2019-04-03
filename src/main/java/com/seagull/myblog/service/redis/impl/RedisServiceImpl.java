package com.seagull.myblog.service.redis.impl;

import com.seagull.myblog.service.redis.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Seagull_gby
 * @date 2019/3/28 16:53
 * Description: Redis 实现
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void set(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setAndTimeOut(Object key, Object value, long timeOut) {
        redisTemplate.opsForValue().set(key, value, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public Object getAndSet(Object key, Object value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public Object get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void expire(Object key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(Object key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(Object key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void sadd(Object key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public Set members(Object key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void hset(Object key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Object hget(Object key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void deleteHsetValue(Object key, Object hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }
}
