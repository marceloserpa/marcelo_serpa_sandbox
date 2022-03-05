package com.marceloserpa.springkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producer")
public class MessageRestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/message")
    public void message(@RequestBody String message) {

        kafkaTemplate.send("messages", message);

    }


}
