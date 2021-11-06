package com.marceloserpa.reactorkafka.receiver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import reactor.kafka.receiver.KafkaReceiver;

public class CalculationReceiver1 {

    private static final Logger LOGGER = LogManager.getLogger(CalculationReceiver1.class);

    private KafkaReceiver<Integer, String> kafkaReceiver;
    private CalculationService calculationService;

    public CalculationReceiver1(KafkaReceiver<Integer, String> kafkaReceiver, CalculationService calculationService) {
        this.kafkaReceiver = kafkaReceiver;
        this.calculationService = calculationService;
    }

    public void start(){
        kafkaReceiver
                .receive()
                .subscribe(r -> {
                    String value = r.value();
                    String[] numbers = value.split("\\+");
                    Integer a = Integer.valueOf(numbers[0]);
                    Integer b = Integer.valueOf(numbers[1]);

                    calculationService.sum(a, b).subscribe(x -> {
                        LOGGER.info(" >> result: " + a + " + " + b + " = " + x);
                        r.receiverOffset().acknowledge();
                    });
                });
    }


}
