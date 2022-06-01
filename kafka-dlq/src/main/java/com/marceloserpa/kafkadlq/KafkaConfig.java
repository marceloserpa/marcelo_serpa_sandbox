package com.marceloserpa.kafkadlq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;


@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2_000, maxDelay = 10_000, multiplier = 2))
    @KafkaListener(groupId = "poc", topics = "marcelo-topic")
    public void listen(String message) throws InterruptedException {
        logger.info("> processing: " + message);
        Thread.sleep(1000);
        if(message.equals("poisonpill")) {
            throw new RuntimeException("ERROR");
        }
    }

    @DltHandler
    public void dltListen(String in) {
        logger.info("> Received from DLT: " + in);
    }

}
