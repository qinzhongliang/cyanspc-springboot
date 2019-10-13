package com.cyan.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CyanConsumer {

    @RabbitListener(queues ="cyanDirectQueue")
    public void consumerMsg(Message message) {
        System.out.println("消费消息:"+message.getPayload().toString());
    }

    @RabbitListener(queues ="cyanFanoutQueue1")
    public void consumerFanoutMsg(Message message) {
        System.out.println("消费消息:"+message.getPayload().toString());
    }

    @RabbitListener(queues ="cyanFanoutQueue2")
    public void consumerFanoutMsg2(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }

    @RabbitListener(queues ="cyanTopicQueue")
    public void consumerTopicMsg(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }

    @RabbitListener(queues ="cyanTopicQueue2")
    public void consumerTopicMsg2(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }
}
