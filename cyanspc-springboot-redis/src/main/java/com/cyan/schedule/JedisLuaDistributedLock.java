package com.cyan.schedule;


import com.cyan.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.commands.JedisCommands;


import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

@Component
public class JedisLuaDistributedLock {

    private final Logger LOGGER = LoggerFactory.getLogger(JedisLuaDistributedLock.class);

    private static String LOCK_PREFIX = "jedisLuaDistributedLock_";

    private DefaultRedisScript<Boolean> lockScript;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisService redisService;

//    public static final String UNLOCK_LUA;
//
//    static {
//        StringBuilder sb = new StringBuilder();
//        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
//        sb.append("then ");
//        sb.append("    return redis.call(\"del\",KEYS[1]) ");
//        sb.append("else ");
//        sb.append("    return 0 ");
//        sb.append("end ");
//        UNLOCK_LUA = sb.toString();
//    }


    @Scheduled(cron = "0/10 * * * * *")
    public void lockJob() {
        String lock = LOCK_PREFIX + JedisLuaDistributedLock.class.getSimpleName();
        boolean flag = false;
        try {
            flag = this.setLock(lock,getHostIp(),600);
            if (flag) {
                LOGGER.info(new Date()+":start lock jedisLuaDistributedLock success");
                Thread.sleep(5000);
            } else {
                String value = (String) redisService.get(lock);
                LOGGER.info(new Date()+":get lock fail,lock belong to:{}",value);
                return;
            }
        } catch (Exception e) {
            LOGGER.error(new Date()+":lock error",e);
        } finally {
            if (flag) {
                LOGGER.info(new Date()+":release lock success");
                releaseLock(lock,getHostIp());
            }
        }
    }

    public boolean setLock(String key,String value, long expire) {
        try {
            Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.set(key.getBytes(), value.getBytes(), Expiration.seconds(expire) ,RedisStringCommands.SetOption.ifAbsent());
                }
            });
            return result;
        } catch (Exception e) {
            LOGGER.error("set redis occured an exception", e);
        }
        return false;
    }

//    public String get(String key) {
//        try {
//            RedisCallback<String> callback = (connection) -> {
//                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
//                return commands.get(key);
//            };
//            String result = redisTemplate.execute(callback);
//            return result;
//        } catch (Exception e) {
//            LOGGER.error("get redis occured an exception", e);
//        }
//        return "";
//    }

    /**
     * 释放锁操作
     * @param key
     * @param value
     * @return
     */
    private boolean releaseLock(String key, String value) {
        lockScript = new DefaultRedisScript<Boolean>();
        lockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        lockScript.setResultType(Boolean.class);
        // 封装参数
        List<Object> keyList = new ArrayList<Object>();
        keyList.add(key);
        keyList.add(value);
        Boolean result = (Boolean) redisTemplate.execute(lockScript, keyList);
        return result;
    }

    /**
     * 获取本机内网IP地址方法
     *
     * @return
     */
    private static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
