package com.marceloserpa.camelkafka.poc.routes;

import org.apache.camel.builder.RouteBuilder;


public class ApplicationRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka://notification?brokers=localhost:9092&consumersCount=1&groupId=notification-app-group&maxPollRecords=5000&seekTo=beginning")
            .routeId("consumer-notification")
            .log("${body}");

    }

}
