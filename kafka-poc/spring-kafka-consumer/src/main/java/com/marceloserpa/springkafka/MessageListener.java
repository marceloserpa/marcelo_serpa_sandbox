package com.marceloserpa.springkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {

    private ObjectMapper objectMapper;

    public MessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "messages")
    public void listen(ConsumerRecord<?, ?> consumerRecord,
                       Acknowledgment acknowledgment) throws InterruptedException {
        Message message = null;
        try {
            message = objectMapper.readValue(consumerRecord.value().toString(), Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(message == null) {
            System.out.println("ERRORRRRRR");
            return;
        }

        Thread.sleep(message.getDelay() * 1000);
        acknowledgment.acknowledge();

        System.out.println(message.getText());
    }


}
