package com.marceloserpa.kafka4.queue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerPoC {

    public static final String TOPIC = "share-group-poc";

    public static void main(String[] args) {

        Properties config = new Properties();

        config.put("bootstrap.servers", "127.0.0.1:9092");
        config.put("acks", "all");
        config.put("key.serializer", StringSerializer.class.getName());
        config.put("value.serializer",StringSerializer.class.getName());

        System.out.println("Creating Producer");
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(config)) {
            for(int i = 0; i < 10; i++) {
                final ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "marcelo", "hello world Kafka 4.0 " + i);
                System.out.println("Sending event");
                producer.send(record, (recordMetadata, error) -> {
                    System.out.println("sent to topic=" + recordMetadata.topic() + " partition=" + recordMetadata.partition());
                });
            }
        }

    }
}
