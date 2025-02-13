package com.ssyx.search.receiver;

import com.rabbitmq.client.Channel;
import com.ssyx.constant.MQConstant;
import com.ssyx.search.service.SkuService;
import com.ssyx.service.RabbitMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class SkuReceiver {
    @Resource
    private SkuService skuService;

    // 商品上架
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MQConstant.QUEUE_GOODS_UPPER, durable = "true"),
            exchange = @Exchange(value = MQConstant.EXCHANGE_GOODS_DIRECT),
            key = {MQConstant.ROUTING_GOODS_UPPER}))
    public void upperSku(Long skuId, Message message, Channel channel) throws IOException {
        if (skuId != null) {
//            调用方法商品上架
            skuService.upperSku(skuId);
        }
//        消息手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    // 商品下架
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MQConstant.QUEUE_GOODS_LOWER, durable = "true"),
            exchange = @Exchange(value = MQConstant.EXCHANGE_GOODS_DIRECT),
            key = {MQConstant.ROUTING_GOODS_LOWER}
    ))
    public void lowerSku(Long skuId, Message message, Channel channel) throws IOException {
        if (null != skuId) {
            skuService.lowerSku(skuId);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}

