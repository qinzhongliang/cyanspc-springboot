package com.cyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启定时任务注解
public class CyanspcSpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyanspcSpringbootRedisApplication.class, args);
    }

}
