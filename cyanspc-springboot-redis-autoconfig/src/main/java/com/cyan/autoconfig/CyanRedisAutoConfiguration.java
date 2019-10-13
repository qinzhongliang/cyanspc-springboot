package com.cyan.autoconfig;

import com.cyan.core.CyanRedis;
import com.cyan.core.CyanRedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自定义启动类配置类
 * Created by smlz on 2019/3/29.
 */
@ConditionalOnClass(value = {Jedis.class, JedisPool.class, JedisPoolConfig.class})
@EnableConfigurationProperties(value = CyanRedisProperties.class)
@Configuration
public class CyanRedisAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(CyanRedisAutoConfiguration.class);

    @Autowired
    private CyanRedisProperties cyanRedisProperties;


    @Bean
    @ConditionalOnProperty(prefix = "spring.cyan.redis",name = "USEHA",havingValue ="false")
    public JedisPool jedisPool() {
        logger.info("自定义启动器加载------------->jedisPool");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(cyanRedisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(cyanRedisProperties.getMaxIdel());
        jedisPoolConfig.setMinIdle(cyanRedisProperties.getMinIdel());
        jedisPoolConfig.setTestOnBorrow(cyanRedisProperties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(cyanRedisProperties.isTestOnRetrun());

        JedisPool  jedisPool = new JedisPool(jedisPoolConfig, cyanRedisProperties.getHost(), cyanRedisProperties.getPort(), cyanRedisProperties.getTimeOut(), cyanRedisProperties.getPassword());
        return jedisPool;
    }

    @Bean
    @ConditionalOnBean(value = JedisPool.class)
    public CyanRedis tulingRedis(JedisPool jedisPool){
        logger.info("加载了单机版的操作类------------>CyanRedisClient");
        CyanRedis tulingRedis = new CyanRedisClient(jedisPool);
        return tulingRedis;
    }

}
