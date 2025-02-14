package com.marceloserpa.spring_3x_kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    @KafkaListener(id = "foo", topics = "orders", clientIdPrefix = "myClientId")
    public void listen(String data) {
        System.out.println(data);
    }
}
