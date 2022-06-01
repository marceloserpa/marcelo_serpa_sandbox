package com.marceloserpa.kafkadlq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.annotation.Backoff;
import org.springframework.util.backoff.FixedBackOff;


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

    @KafkaListener(id = "dltPoc", topics = "marcelo-topic.DLT")
    public void dltListen(String in) {
        logger.info("> Received from DLT: " + in);
    }

}
