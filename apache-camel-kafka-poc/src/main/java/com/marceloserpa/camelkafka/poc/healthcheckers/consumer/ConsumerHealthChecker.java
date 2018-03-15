package com.marceloserpa.camelkafka.poc.healthcheckers.consumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;

public class ConsumerHealthChecker {

    private static final String URL_KAFKA_SERVERS = "localhost:9092";
    private static final String HEALTH_TOPIC = "health";
    private static final String HEALTH_CHECK_GROUP_ID = "group-id-health-check-consumer";
    private static final long HEALTH_TIMEOUT = 7000;

    public boolean check(){
        Properties props = getProperties();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try(KafkaConsumer consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList(HEALTH_TOPIC));
            Future<Boolean> future = executor.submit((Callable) () -> {
                consumer.poll(1000);
                return true;
            });
            return future.get(HEALTH_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            return false;
        } finally {
            executor.shutdownNow();
        }
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", URL_KAFKA_SERVERS);
        props.put("group.id", HEALTH_CHECK_GROUP_ID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "0");
        props.put("request.timeout.ms", "7000");
        props.put("session.timeout.ms", "6000");
        props.put("consumer.timeout.ms","3000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

}
