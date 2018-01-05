package com.ldy.rabbit;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import java.util.Map;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class MessageHandler implements ChannelAwareMessageListener{

    public static final java.util.Random random=new java.util.Random();

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("mq接收消息start============");
        Long startTime = System.currentTimeMillis();
        try {
            String msg  = new String(message.getBody());
            System.out.println(msg);
            Map<String, Object> paraMap = JSON.parseObject(msg);
            String a = String.valueOf(paraMap.get("a"));
            String b = String.valueOf(paraMap.get("b"));
            boolean flag = true;
            /*if(flag){
                for (;;) {}
            }*/

            //Thread.currentThread().sleep(1000);

//			int a = random.nextInt();
//			a=2;
//			if(a%2 == 0){
//				throw new RuntimeException();
//			}

            // List<MessageInfo> messagelist = (List<MessageInfo>)
            // paraMap.get("messagelist");
            // noticeHttpClient.doSmsSend("02", 0L,
            // String.valueOf(paraMap.get("phone")),
            // addressee+constantsConfig.receiveQuotationMessage,
            // String.valueOf(memberId));
            Long endTime = System.currentTimeMillis();
            System.out.println("mq接收消息end============执行时间-------：" + (endTime - startTime) + " ms");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }

    }

}
