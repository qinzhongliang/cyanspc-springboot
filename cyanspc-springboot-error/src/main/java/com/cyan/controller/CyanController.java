package com.cyan.controller;

import com.cyan.exception.CyanException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyanController {

    @RequestMapping(value = "cyanHello")
    public String cyanHello(){
        return "OK";
    }

    @RequestMapping(value = "testError")
    public String testError(){
        throw new CyanException(-1,"服务器内部错误");
    }

}
