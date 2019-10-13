package com.cyan.producter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CyanProductor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送到直接交换机上
     * @param msg
     */
    public void sendMsg(String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("sex","男");
        map.put("msg",msg);
        rabbitTemplate.convertAndSend("cyanDirectExchange","cyan.directkey",map);
    }

    /**
     * 发送到扇形交换机上
     * @param msg
     */
    public void sendMsg2Fanout(String msg) {
        rabbitTemplate.convertAndSend("cyanFanoutExchange","aaaabbdd",msg);

    }


    /**
     * 发送到扇形交换机上
     * @param msg
     */
    public void sendMsg2Topic(String msg) {
        rabbitTemplate.convertAndSend("cyanTopicExchange","topic.key.aaa",msg);
        rabbitTemplate.convertAndSend("cyanTopicExchange","aa.key",msg);

    }

}
