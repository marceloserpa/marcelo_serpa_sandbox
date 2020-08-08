package com.marceloserpa.reactor.rabbitmq.app;

import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

public class SampleReceiver {

  private static final String QUEUE = "sample-queue";

  private final Receiver receiver;
  private final Sender sender;

  public SampleReceiver() {
    this.receiver = RabbitFlux.createReceiver();
    this.sender = RabbitFlux.createSender();
  }

  public SampleReceiver(Receiver receiver, Sender sender) {
    this.receiver = receiver;
    this.sender = sender;
  }

  public void start() {
    receiver
        .consumeManualAck(QUEUE)
        .subscribe(
            m -> {
              String message = new String(m.getBody());
              if (message.contains("19")) {
                m.nack(false);
              } else {
                m.ack();
                System.out.println("Received message " + message);
              }
            });
  }

  public void close() {
    this.sender.close();
    this.receiver.close();
  }

  public static void main(String[] args) throws Exception {
    new SampleReceiver().start();
  }
}
