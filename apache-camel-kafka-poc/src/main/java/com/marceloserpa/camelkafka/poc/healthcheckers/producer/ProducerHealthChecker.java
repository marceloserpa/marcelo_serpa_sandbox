package com.marceloserpa.camelkafka.poc.healthcheckers.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ProducerHealthChecker {

    private static final String URL_KAFKA_SERVERS = "localhost:9092";
    private static final String ACKS = "1";
    private static final String HEALTH_TOPIC = "health";
    private static final long HEALTH_TIMEOUT = 7000;

    public boolean check(){
        Properties props = getProperties();
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)){
            ProducerRecord<String, String> record = new ProducerRecord<>(HEALTH_TOPIC, "health-check: up");
            producer.send(record).get(HEALTH_TIMEOUT, TimeUnit.MILLISECONDS);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", URL_KAFKA_SERVERS);
        props.put("acks", ACKS);
        props.put("retries", 0);
        props.put("batch.size", 2);
        props.put("timeout.ms", 2000);
        props.put("max.block.ms", 1000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }


}
