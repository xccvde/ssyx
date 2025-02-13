package com.ssyx.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RabbitMQService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息，也可以不封装直接使用 rabbitTemplate.convertAndSend
     * @param exchange 交换机
     * @param routingKey 路由key
     * @param message 发送的消息队列
     * @return ture
     */
    public boolean sendMessage(String exchange,String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }
}
