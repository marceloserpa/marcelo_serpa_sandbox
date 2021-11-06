package com.marceloserpa.reactorkafka.receiver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import reactor.kafka.receiver.KafkaReceiver;

public class Receiver {

    private static final Logger LOGGER = LogManager.getLogger(Receiver.class);

    private KafkaReceiver<Integer, String> integerStringKafkaReceiver;
    private CalculationService calculationService;

    public Receiver(KafkaReceiver<Integer, String> integerStringKafkaReceiver, CalculationService calculationService) {
        this.integerStringKafkaReceiver = integerStringKafkaReceiver;
        this.calculationService = calculationService;
    }

    public void start(){
        integerStringKafkaReceiver
                .receive()
                .subscribe(r -> {
                    String value = r.value();
                    String[] numbers = value.split("\\+");
                    Integer a = Integer.valueOf(numbers[0]);
                    Integer b = Integer.valueOf(numbers[1]);

                    calculationService.sum(a, b).subscribe(x -> {
                        LOGGER.info(" >> result: " + a + " + " + b + " = " + x);
                        //System.out.printf(">>> Received message: %s\n", r);
                        r.receiverOffset().acknowledge();
                    });
                });
    }


}
