package com.seagull.myblog.service.redis;

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

}
