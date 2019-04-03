package com.seagull.myblog.service.redis;

import java.util.Set;

/**
 * @author Seagull_gby
 * @date 2019/3/28 16:53
 * Description: Redis 接口
 */
public interface RedisService {

    /**
     * set存数据
     * @param key
     * @param value
     */
    public void set(Object key, Object value);

    /**
     * 存数据并设置过期时间（秒级）
     * @param key 键
     * @param value 值
     * @param timeOut 过期时间（秒）
     */
    public void setAndTimeOut(Object key, Object value, long timeOut);

    /**
     * 设置键的字符串并返回旧值
     * @param key 键
     * @param value 新值
     * @return 旧值
     */
    public Object getAndSet(Object key, Object value);

    /**
     * get获取数据
     * @param key
     */
    public Object get(Object key);

    /**
     * 设置有效秒数
     * @param key
     * @param expire 秒数
     */
    public void expire(Object key, long expire);

    /**
     * 移除数据
     * @param key
     */
    public void remove(Object key);

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false 不存在
     */
    public boolean hasKey(Object key);

    /**
     * 添加一个值到对应键的set集合中
     * @param key 键
     * @param value 值
     */
    public void sadd(Object key, Object value);

    /**
     * 获得某个键中的值的集合
     * @param key 键
     * @return value集合
     */
    public Set members(Object key);

    /**
     * 添加map到hset中
     * @param key 键
     * @param hashKey map的键
     * @param value map的值
     */
    public void hset(Object key, Object hashKey, Object value);

    /**
     * 根据key和map的key取值
     * @param key 键
     * @param hashKey map的键
     * @return 对应的值
     */
    public Object hget(Object key, Object hashKey);

    /**
     * 删除对应key和map的key的值
     * @param key 键
     * @param hashKey map的键
     */
    public void deleteHsetValue(Object key, Object hashKey);
}
