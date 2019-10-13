package com.cyan.service;

/**
 * Created by cyan
 * Date:2019/9/21 10:59
 */
public interface RedisService {
    boolean set(final String key, Object value);
    Object get(final String key);
    void remove(final String key);
    boolean exists(final String key);
    boolean decr(final String key, int value);
}
