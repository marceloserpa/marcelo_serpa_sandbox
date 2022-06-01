package com.marceloserpa.multithreadconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @KafkaListener(groupId = "poc", topics = "marcelo-topic", concurrency = "10")
    public void listen(String message) throws InterruptedException {
        logger.info("> processing: " + message);
        Thread.sleep(30_000);
        if(message.equals("poisonpill")) {
            throw new RuntimeException("ERROR");
        }
    }

    @DltHandler
    public void dltListen(String in) {
        logger.info("> Received from DLT: " + in);
    }

}
