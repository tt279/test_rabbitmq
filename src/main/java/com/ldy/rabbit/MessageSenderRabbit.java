package com.ldy.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderRabbit {

    // amqpTemplate 消息模板
    @Autowired
    private AmqpTemplate amqpTemplate;
    // exchange和queues链接的Key
    @Value("${routing_key}")
    private String routingKey;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public void sendDataToExchange(String str) {
        // 通过amqpTemplate模板来访问监听容器
        // MessageProperties messageProperties = new MessageProperties();
        // messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        // Message message = new Message(str.getBytes(), messageProperties);
        // amqpTemplate.convertAndSend("this.routingKey",message);
        System.out.println("routingKey:" + this.routingKey);
        amqpTemplate.convertAndSend(this.routingKey, str);
    }

}