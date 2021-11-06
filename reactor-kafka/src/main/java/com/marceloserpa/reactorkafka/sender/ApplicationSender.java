package com.marceloserpa.reactorkafka.sender;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import reactor.core.publisher.Flux;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ApplicationSender {

    public static void main(String[] args) {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        SenderOptions<Integer, String> senderOptions =
                SenderOptions.<Integer, String>create(producerProps)
                        .maxInFlight(1024);

        String topic = "messages";
        Long timestamp = System.currentTimeMillis();

        KafkaSender<Integer, String> sender = KafkaSender.create(senderOptions);

        Flux<SenderRecord<Integer, String, Integer>> outboundFlux =
                Flux.range(1, 10)
                        .map(i -> {
                            Random generator = new Random();
                            int a = generator.nextInt(1000) + 1;
                            int b = generator.nextInt(1000) + 1;
                            String message = a + "+" + b;

                            return SenderRecord.create(topic, null, timestamp, i, message, i);
                        });

        sender.send(outboundFlux)
                .doOnNext(r -> System.out.printf("Message #%d send response: %s\n", r.correlationMetadata(), r.recordMetadata()))
                .subscribe();
    }

}
