package com.cyan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyanController {

    @RequestMapping(value = "testWar")
    public String testWar(){
        return "hello war";
    }

}
