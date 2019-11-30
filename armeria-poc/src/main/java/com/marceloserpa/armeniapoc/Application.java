package com.marceloserpa.armeniapoc;

import java.util.concurrent.CompletableFuture;

import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.ServerPort;

public class Application {

	public static void main(String[] args) {
		ServerBuilder sb = Server.builder();
		sb.annotatedService(new BookApi());
		sb.port(new ServerPort(9020, SessionProtocol.HTTP));
        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join(); 

	}
	
	
}
