package com.cyan.schedule;

import com.cyan.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by cyan
 * Date:2019/9/21 10:56
 */
@Service
public class LockNxExJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockNxExJob.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static String LOCK_PREFIX = "prefix_";

    //@Scheduled(cron = "0/10 * * * * *")
    public void lockJob(){
        String lock = LOCK_PREFIX + LockNxExJob.class.getSimpleName();
        boolean flag = false;
        try {
            flag = redisTemplate.opsForValue().setIfAbsent(lock,getHostIp());
            if(flag){
                redisTemplate.opsForValue().set(lock,getHostIp(),3600);
                LOGGER.info(new Date()+":start lock lockNxExJob success");
                Thread.sleep(5000);
            }else{
                String value = (String)redisService.get(lock);
                LOGGER.info(new Date()+":get lock fail,lock belong to:{}",value);
                return;
            }
        } catch (Exception e) {
            LOGGER.error(new Date()+":lock error",e);
        }finally {
            if(flag){
                LOGGER.info(new Date()+":release lock success");
                redisService.remove(lock);
            }
        }
    }


    /**
     * 获取本机内网IP地址方法
     * @return
     */
    private static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String localIP = "";
        try {
            localIP = getHostIp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取本机IP
        System.out.println(localIP);
        System.out.println(LockNxExJob.class.getSimpleName());
    }
}
