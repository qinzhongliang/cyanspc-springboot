package com.cyan.controller;

import com.cyan.producter.CyanProductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestMqController {

    @Autowired
    private CyanProductor cyanProductor;

    @RequestMapping("/testMqSender/{msg}")
    public String testMqSender(@PathVariable("msg") String msg) {
        cyanProductor.sendMsg(msg);
        return "OK";
    }

    @RequestMapping("/testFanoutMqSender/{msg}")
    public String testFanoutMqSender(@PathVariable("msg") String msg) {
        cyanProductor.sendMsg2Fanout(msg);
        return "OK";
    }

    @RequestMapping("/testTopicSender/{msg}")
    public String testTopicSender(@PathVariable("msg") String msg) {
        cyanProductor.sendMsg2Topic(msg);
        return "OK";
    }

}
