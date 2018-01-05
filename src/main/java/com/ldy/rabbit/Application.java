package com.ldy.rabbit;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException {

        @SuppressWarnings("resource")
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("springContext.xml");
        System.out.println("start-----------");
        ctx.start();

   /*  ThreadPoolTaskExecutor threadPoolTaskExecutor = ctx.getBean(ThreadPoolTaskExecutor.class);

       for (int i = 0; i < 10; i++) {
            threadPoolTaskExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("test线程：");
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {}
            }
        }).start();*/

        MessageSenderRabbit messageSenderRabbit = ctx.getBean(MessageSenderRabbit.class);
        Map<String, String> map = new HashMap<>();
        map.put("name", "ldy");

        messageSenderRabbit.sendDataToExchange(JSON.toJSONString(map));
        System.out.println("end-----------");
        System.in.read();
       // ctx.destroy();
    }

}
