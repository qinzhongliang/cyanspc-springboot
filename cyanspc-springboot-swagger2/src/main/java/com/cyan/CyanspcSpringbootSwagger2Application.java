package com.cyan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CyanspcSpringbootSwagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(CyanspcSpringbootSwagger2Application.class, args);
    }

}
