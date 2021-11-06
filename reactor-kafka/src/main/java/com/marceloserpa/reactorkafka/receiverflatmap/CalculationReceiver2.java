package com.marceloserpa.reactorkafka.receiverflatmap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.util.function.Tuples;

public class CalculationReceiver2 {

    private static final Logger LOGGER = LogManager.getLogger(CalculationReceiver2.class);

    private KafkaReceiver<Integer, String> kafkaReceiver;
    private CalculationService calculationService;

    public CalculationReceiver2(KafkaReceiver<Integer, String> kafkaReceiver, CalculationService calculationService) {
        this.kafkaReceiver = kafkaReceiver;
        this.calculationService = calculationService;
    }

    public void start() {
        kafkaReceiver
                .receive()
                .flatMap(record -> {
                    String[] numbers = record.value().split("\\+");
                    Integer a = Integer.valueOf(numbers[0]);
                    Integer b = Integer.valueOf(numbers[1]);

                    return calculationService.sum(a, b)
                            .flatMap(result -> Mono.just(Tuples.of(record, a, b, result)));
                })
                .flatMap(result -> {
                    String message = " >> result: " + result.getT2() + " + " + result.getT3() + " = " + result.getT4();
                    LOGGER.info(message);
                    result.getT1().receiverOffset().acknowledge();
                    return Mono.empty();
                })
                .subscribe();

    }


}
