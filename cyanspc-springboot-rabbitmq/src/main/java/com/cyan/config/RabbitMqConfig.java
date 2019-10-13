package com.cyan.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 直接交换机
     * @return
     */
    @Bean
    public DirectExchange cyanDirectExchange(){
        //参数 durable:表示消息是否可持久化
        //autoDelete:表示若没有队列和此交换机绑定 就直接删除该交换机
        return new DirectExchange("cyanDirectExchange",true,false);
    }

    @Bean
    public Queue cyanDirectQueue(){
        return new Queue("cyanDirectQueue",true,false,false);
    }

    @Bean
    public Binding tulingDq2De(){
        return BindingBuilder.bind(cyanDirectQueue()).to(cyanDirectExchange()).with("cyan.directkey");
    }

    /**
     * 扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange cyanFanoutExchange() {
        return new FanoutExchange("cyanFanoutExchange",true,false);
    }

    @Bean
    public Queue cyanFanoutQueue1() {
        return new Queue("cyanFanoutQueue1",true,false,false);
    }

    @Bean
    public Queue cyanFanoutQueue2() {
        return new Queue("cyanFanoutQueue2",true,false,false);
    }

    @Bean
    public Binding cyanBind1() {
        return BindingBuilder.bind(cyanFanoutQueue1()).to(cyanFanoutExchange());

    }

    @Bean
    public Binding cyanBind2() {
        return BindingBuilder.bind(cyanFanoutQueue2()).to(cyanFanoutExchange());
    }

    /**
     * 主题交换机
     */
    @Bean
    public TopicExchange cyanTopicExchange() {
        return new TopicExchange("cyanTopicExchange",true,false);
    }

    @Bean
    public Queue cyanTopicQueue() {
        return new Queue("cyanTopicQueue",true,false,false);
    }

    @Bean
    public Queue cyanTopicQueue2() {
        return new Queue("cyanTopicQueue2",true,false,false);
    }

    @Bean
    public Binding topicBind1(){
        return BindingBuilder.bind(cyanTopicQueue()).to(cyanTopicExchange()).with("topic.key.#");

    }

    @Bean
    public Binding topicBind2(){
        return BindingBuilder.bind(cyanTopicQueue2()).to(cyanTopicExchange()).with("#.key");
    }

}

