package com.marceloserpa.camelkafka.poc;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class Application {

    private Main main;

    public static void main(String[] args) throws Exception {
        Application example = new Application();
        example.boot();
    }

    public void boot() throws Exception {
        String uri = "kafka:localhost:9092?topic=test&groupId=health";

        main = new Main();
        main.addRouteBuilder(new RouteBuilder() {
            public void configure() throws Exception {
                from(uri)
                        .process(exchange -> System.out.println("sss"))
                    .end();
            }
        });
        main.start();
    }

}
