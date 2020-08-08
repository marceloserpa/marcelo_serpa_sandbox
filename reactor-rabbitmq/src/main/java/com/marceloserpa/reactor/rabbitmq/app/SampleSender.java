package com.marceloserpa.reactor.rabbitmq.app;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.OutboundMessageResult;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;

public class SampleSender {
	
    private static final String QUEUE = "sample-queue";

    private final Sender sender;

    public SampleSender() {
        this.sender = RabbitFlux.createSender();
    }

    public void send(String queue, int count, CountDownLatch latch) {
        Flux<OutboundMessageResult> confirmations = sender.sendWithPublishConfirms(Flux.range(1, count)
            .map(i -> new OutboundMessage("", queue, ("Message_" + i).getBytes())));

        sender.declareQueue(QueueSpecification.queue(queue))
            .thenMany(confirmations)
                .doOnError(e -> e.printStackTrace())
                .subscribe(r -> {
                    if (r.isAck()) {
                        System.out.println("Message "+ new String(r.getOutboundMessage().getBody() +" sent successfully"));
                        latch.countDown();
                    }
                });
    }

    public void close() {
        this.sender.close();
    }

    public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        SampleSender sender = new SampleSender();
        sender.send(QUEUE, count, latch);
        latch.await(10, TimeUnit.SECONDS);
        sender.close();
    }
}
