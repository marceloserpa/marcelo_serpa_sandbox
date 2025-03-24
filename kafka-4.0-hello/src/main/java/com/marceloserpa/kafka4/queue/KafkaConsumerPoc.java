package com.marceloserpa.kafka4.queue;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static com.marceloserpa.kafka4.queue.KafkaProducerPoC.TOPIC;
import static java.time.temporal.ChronoUnit.SECONDS;

public class KafkaConsumerPoc {

    public static void main(String[] args) {
        String groupId = "share-group";

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "127.0.0.1:9092");
        props.setProperty("group.id", groupId);
        props.setProperty("auto.offset.reset", "earliest");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (KafkaShareConsumer<String, String> consumer = new KafkaShareConsumer<>(props)) {
            System.out.println("STARTING");
            consumer.subscribe(Collections.singletonList(TOPIC));
            while (true) {
                System.out.println("running");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));

                if(records.isEmpty()) {
                    System.out.println("It is Empty!!");
                } else {
                    for (ConsumerRecord<String, String> record : records)
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}