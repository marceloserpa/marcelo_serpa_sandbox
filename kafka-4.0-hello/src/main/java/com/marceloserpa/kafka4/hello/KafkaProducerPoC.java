package com.marceloserpa.kafka4.hello;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerPoC {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties config = new Properties();

        config.put("bootstrap.servers", "192.168.160.2:9092");
        config.put("acks", "all");
        config.put("key.serializer", StringSerializer.class.getName());
        config.put("value.serializer",StringSerializer.class.getName());

        System.out.println("Creating Producer");
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(config)) {
            var topic = "hello";

            final ProducerRecord<String, String> record = new ProducerRecord<>(topic, "marcelo", "hello world Kafka 4.0");
            System.out.println("Sending event");
            final Future<RecordMetadata> future = producer.send(record);
            System.out.println("block future");
            future.get();
            System.out.println("Sent event");
        }



    }
}
