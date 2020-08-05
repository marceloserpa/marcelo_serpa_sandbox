package com.mserpa.reactor.netty.poc;

import java.util.function.BiFunction;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class Application {

	private final static int SERVER_PORT= 9080;
	
	public static void main(String[] args) {
				
		HttpServer.create()
			.port(SERVER_PORT)
			.route(routes ->
	        	routes.get("/test/{param}", getTest())
	        		  .get("/hello/{message}", getHello())
			)			
			.bindNow()
			.onDispose()
			.block();
	
	}

	private static BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> getTest() {
		return (req, res) -> res.sendString(Mono.just("param: " + req.param("param")));
	}
	
	private static BiFunction<? super HttpServerRequest, ? super HttpServerResponse, ? extends Publisher<Void>> getHello() {
		return (req, res) -> res.sendString(Mono.just("hello " + req.param("message") + "!!!"));
	}
	
}
