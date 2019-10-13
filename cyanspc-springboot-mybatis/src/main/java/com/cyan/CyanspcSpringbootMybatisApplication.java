package com.cyan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.cyan.mapper"})
public class CyanspcSpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CyanspcSpringbootMybatisApplication.class, args);
    }

}
