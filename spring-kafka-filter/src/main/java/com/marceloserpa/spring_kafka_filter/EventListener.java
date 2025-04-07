package com.marceloserpa.spring_kafka_filter;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @KafkaListener(topics = "events", groupId = "filter-listener")
    public void listen(String event){
        System.out.println(event);
    }

}
