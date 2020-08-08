package com.marceloserpa.reactor.rabbitmq.app;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import reactor.core.publisher.Flux;
import reactor.rabbitmq.AcknowledgableDelivery;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

public class SampleReceiverTest {

  @Test
  public void shouldNackInvalidMessage() throws Exception {
    AcknowledgableDelivery mockMessage = mock(AcknowledgableDelivery.class);
    when(mockMessage.getBody()).thenReturn("Message_19".getBytes());
    doNothing().when(mockMessage).nack(anyBoolean());

    Receiver mockReceiver = mock(Receiver.class);
    Sender mockSender = mock(Sender.class);
    when(mockReceiver.consumeManualAck(anyString()))
        .thenReturn(Flux.<AcknowledgableDelivery>just(mockMessage));
    doNothing().when(mockReceiver).close();
    doNothing().when(mockSender).close();
    
    SampleReceiver sampleReceiver = new SampleReceiver(mockReceiver, mockSender);

    sampleReceiver.start();
    
    verify(mockMessage).nack(false);
  }
  
  @Test
  public void shouldAckValidMessage() throws Exception {
    AcknowledgableDelivery mockMessage = mock(AcknowledgableDelivery.class);
    when(mockMessage.getBody()).thenReturn("Message_20".getBytes());
    doNothing().when(mockMessage).ack();

    Receiver mockReceiver = mock(Receiver.class);
    Sender mockSender = mock(Sender.class);
    when(mockReceiver.consumeManualAck(anyString()))
        .thenReturn(Flux.<AcknowledgableDelivery>just(mockMessage));
    doNothing().when(mockReceiver).close();
    doNothing().when(mockSender).close();
    
    SampleReceiver sampleReceiver = new SampleReceiver(mockReceiver, mockSender);

    sampleReceiver.start();
    
    verify(mockMessage).ack();
  }  
}
