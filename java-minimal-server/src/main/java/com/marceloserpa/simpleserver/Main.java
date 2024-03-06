package com.marceloserpa.simpleserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        var port = new InetSocketAddress(9080);
        HttpServer httpServer = HttpServer.create(port, 0);
        httpServer.createContext("/hello", (exchange) -> {
            var message = "Hello World";
            exchange.sendResponseHeaders(200, message.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(message.getBytes());
            outputStream.close();
        });

        httpServer.start();
    }
}