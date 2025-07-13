package com.marceloserpa.schemaregistry.poc;

import com.marceloserpa.orders.events.OrderPlaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<String, OrderPlaced> kafkaTemplate;

    public void publishOrderPlacedEvent(String topic, String key, OrderPlaced orderPlaced) {
        kafkaTemplate.send(topic, key, orderPlaced);
    }
}
