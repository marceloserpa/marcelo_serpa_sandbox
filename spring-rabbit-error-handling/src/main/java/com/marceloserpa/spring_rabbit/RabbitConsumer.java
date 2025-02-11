package com.marceloserpa.spring_rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {


    @RabbitListener(queues = "orders.queue")
    public void receive(Order order)  {
        if(order.price() < 1) {
            System.out.println("error");
            throw new RuntimeException("invalid price");
        }
        System.out.println(order.price());
    }

}
