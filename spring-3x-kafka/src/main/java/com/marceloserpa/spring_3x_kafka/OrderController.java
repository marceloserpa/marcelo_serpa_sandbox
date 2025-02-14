package com.marceloserpa.spring_3x_kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/orders")
    public void create(@RequestBody Order order) {
        kafkaTemplate.send("orders", order);
    }


}
