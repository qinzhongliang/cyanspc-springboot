package com.cyan.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CyanRedisClient implements CyanRedis {

    private Logger logger = LoggerFactory.getLogger(CyanRedisClient.class);

    private JedisPool jedisPool;

    public CyanRedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
        } catch (Exception e) {
            logger.error("set key:{},value:{},error:{}", key, value, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error("get key:{}error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }
}
