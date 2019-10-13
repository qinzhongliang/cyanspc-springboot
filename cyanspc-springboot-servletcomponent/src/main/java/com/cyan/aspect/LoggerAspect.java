package com.cyan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(com.cyan.anno.AngleLogger)")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        LOGGER.info("方法名:{}",joinPoint.getSignature().getName());
        LOGGER.info("参数:{}",joinPoint.getArgs());
    }
}
