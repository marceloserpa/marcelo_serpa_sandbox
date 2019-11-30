package com.marceloserpa.armeniapoc;

import java.util.concurrent.CompletableFuture;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;

public class Application {

	public static void main(String[] args) {
		ServerBuilder sb = Server.builder();
		sb.annotatedService(new BookApi());
        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join(); 

	}
	
	
}
