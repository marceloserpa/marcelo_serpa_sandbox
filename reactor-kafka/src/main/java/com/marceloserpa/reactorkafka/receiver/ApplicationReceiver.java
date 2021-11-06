package com.marceloserpa.reactorkafka.receiver;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationReceiver {


    public static void main(String[] args) {

        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        ReceiverOptions<Integer, String> receiverOptions =
                ReceiverOptions.<Integer, String>create(consumerProps)
                        .subscription(Collections.singleton("messages"));


        KafkaReceiver<Integer, String> integerStringKafkaReceiver = KafkaReceiver.create(receiverOptions);
        CalculationService calculationService = new CalculationService();

        Receiver receiver = new Receiver(integerStringKafkaReceiver, calculationService);
        receiver.start();


    }


}
