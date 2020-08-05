package com.spring.webflux.jdbc;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BookRouter {

	@Bean
	public RouterFunction<ServerResponse> route(BookHandler handler) {
		return RouterFunctions
				.route(GET("/books").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::getAll)
				.andRoute(POST("/books").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::createNewBook)
		        .andRoute(DELETE("/books/id/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::deleteBook)
		;
	}

}
