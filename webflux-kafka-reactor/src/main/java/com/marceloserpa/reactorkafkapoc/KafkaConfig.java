package com.marceloserpa.reactorkafkapoc;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class.getName());

    @Bean
    public KafkaSender<String, Sale> kafkaSender(){
        log.info("Starting kafka sender");
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SaleSerializer.class);
        SenderOptions<String, Sale> senderOptions = SenderOptions.create(props);
        return KafkaSender.create(senderOptions);
    }



}
