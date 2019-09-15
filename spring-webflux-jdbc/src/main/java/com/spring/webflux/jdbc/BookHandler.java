package com.spring.webflux.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {

	@Autowired
	private BookRepository repository;

	public Mono<ServerResponse> getAll(ServerRequest request) {
		Flux<BookModel> books = repository.getAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(books, BookModel.class);
	}

}
