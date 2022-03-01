package com.marceloserpa.kafkaavro;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerPoC {

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");


        KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<>(properties);

        Customer customer = Customer.newBuilder()
                .setFirstName("Marcelo")
                .setLastName("Serpa2")
                .setAge(100)
                .setPhoneNumber("58 9 9999-9999")
                .setEmail("marcelo@github.com")
                .setWeight(80f)
                .setHeight(178f)
                .build();

        String topic = "customer-avro";

        ProducerRecord<String, Customer> producerRecord =new ProducerRecord<> (topic, customer);

        System.out.println(customer);
        kafkaProducer.send(producerRecord, (metadata, exception) -> {
            if (exception == null) {
                System.out.println(metadata);
            } else {
                exception.printStackTrace();
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();

    }


}
