package com.marceloserpa.reactorkafkapoc;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
public class SaleService {

    private static final Logger log = LoggerFactory.getLogger(SaleService.class.getName());
    private static final String SALE_TOPIC = "sales";

    private KafkaSender<String, Sale> kafkaSender;

    public SaleService(KafkaSender<String, Sale> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public Mono<Boolean> sale(Sale sale){
        ProducerRecord<String, Sale> record = new ProducerRecord<>(SALE_TOPIC, sale.getId().toString(), sale);
        SenderRecord<String, Sale, String> senderRecord = SenderRecord.create(record, sale.getId().toString());
        return kafkaSender.send(Mono.just(senderRecord))
                .doOnNext(r -> {
                    String id = r.correlationMetadata();
                    log.trace("Message sent to kafka", id);
                })
                .then(Mono.just(true))
                .onErrorReturn(false);
    }

}
