package com.marceloserpa.reactorkafka.receiverflatmap;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;

import static org.mockito.Mockito.*;

class CalculationReceiver2Test {


    @Test
    public void processMessageSuccessfully(){
        String messageToProcess = "2+3";

        KafkaReceiver<Integer, String> kafkaReceiver = (KafkaReceiver<Integer, String>) Mockito.mock(KafkaReceiver.class);
        CalculationService calculationService = Mockito.mock(CalculationService.class);
        when(calculationService.sum(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Mono.just(5));

        ReceiverRecord<Integer, String> record = (ReceiverRecord<Integer, String> ) mock(ReceiverRecord.class);
        when(kafkaReceiver.receive()).thenReturn(Flux.just(record));
        when(record.value()).thenReturn(messageToProcess);

        ReceiverOffset offset = mock(ReceiverOffset.class);
        when(record.receiverOffset()).thenReturn(offset);

        new CalculationReceiver2(kafkaReceiver, calculationService).start();

        verify(calculationService).sum(eq(2), eq(3));
    }



}