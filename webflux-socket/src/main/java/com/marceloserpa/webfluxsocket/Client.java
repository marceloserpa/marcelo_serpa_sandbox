package com.marceloserpa.webfluxsocket;


import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
                        URI.create("ws://localhost:8080/publisher"),
                        session -> session.send(
                                        Mono.just(session.textMessage("poc")))
                                .thenMany(session.receive()
                                        .map(WebSocketMessage::getPayloadAsText)
                                        .doOnNext(message -> System.out.println("--------" + message))
                                        .log())
                                .then())
                .block(Duration.ofSeconds(50L));

    }
}