package com.cyan.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AngleLogger {
}
