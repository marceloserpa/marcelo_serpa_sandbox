package com.marceloserpa.webfluxsocket.socket;

import com.marceloserpa.webfluxsocket.service.EventGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PublisherHandler implements WebSocketHandler {

    @Autowired
    EventGeneratorService eventGeneratorService;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> messages = eventGeneratorService.getMessages()
                .flatMap(o -> {
                  return Mono.just(o);
                })
                .map(session::textMessage);

        return session.send(messages);
    }

}