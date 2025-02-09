package com.marceloserpa.springrabbitfun;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = "test-queue")
    public void receive(String content) {

        System.out.println(content);

    }

}
