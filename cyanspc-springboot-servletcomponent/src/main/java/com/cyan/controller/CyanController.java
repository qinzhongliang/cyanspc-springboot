package com.cyan.controller;

import com.cyan.anno.AngleLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyanController {

    @RequestMapping(value = "cyanHello")
    @AngleLogger
    public String cyanHello(String name){
        System.out.println("name:"+name);
        return "OK";
    }

}
