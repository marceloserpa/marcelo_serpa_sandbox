package com.marceloserpa.spring_kafka_filter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements RecordFilterStrategy {

    @Override
    public boolean filter(ConsumerRecord consumerRecord) {

        var message = consumerRecord.value().toString();

        return message.contains("ignore-me");
    }
}
