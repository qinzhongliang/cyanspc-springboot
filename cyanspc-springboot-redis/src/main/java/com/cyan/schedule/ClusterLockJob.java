package com.cyan.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClusterLockJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterLockJob.class);

    //@Scheduled(cron="0/5 * * * * *")//秒分时日月年
    public void executeJob(){
        LOGGER.info("当前时间："+new Date());
    }
}
