package com.marceloserpa.camelkafka.poc.healthcheckers;

import com.marceloserpa.camelkafka.poc.healthcheckers.consumer.ConsumerHealthChecker;
import com.marceloserpa.camelkafka.poc.healthcheckers.models.Health;
import com.marceloserpa.camelkafka.poc.healthcheckers.models.Metrics;
import com.marceloserpa.camelkafka.poc.healthcheckers.parser.JsonParser;
import com.marceloserpa.camelkafka.poc.healthcheckers.producer.ProducerHealthChecker;
import org.apache.camel.builder.RouteBuilder;
import java.io.IOException;

public class HttpHealthCheckRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        onException(IOException.class)
                .maximumRedeliveries(0).redeliveryDelay(0);

        from("jetty://http://localhost:8888/health")
            .to("direct:health-check-consumer")
            .to("direct:health-check-producer")
            .process(exchange -> {
                Health health = new Health();
                health.setKafkaConsumer(new Metrics(exchange.getIn().getHeader("consumer").toString()));
                health.setKafkaProducer(new Metrics(exchange.getIn().getHeader("producer").toString()));
                if("DOWN".equals(health.getKafkaConsumer().getStatus()) || "DOWN".equals(health.getKafkaProducer().getStatus())){
                    health.setStatus("DOWN");
                } else {
                    health.setStatus("UP");
                }
                exchange.getOut().setHeader("Content-Type", "application/json");
                exchange.getOut().setBody(new JsonParser().parse(health));
            })

        .end();


        from("direct:health-check-consumer")
            .process(exchange -> {
                exchange.getOut().setHeaders(exchange.getIn().getHeaders());
                ConsumerHealthChecker consumerHealthChecker = new ConsumerHealthChecker();
                exchange.getOut().setHeader("consumer", consumerHealthChecker.check() ? "UP" : "DOWN");
            })
        .end();

        from("direct:health-check-producer")
            .setBody().constant("health-check-consumer-ping")
            .process(exchange -> {
                exchange.getOut().setHeaders(exchange.getIn().getHeaders());
                ProducerHealthChecker producerHealthChecker = new ProducerHealthChecker();
                exchange.getOut().setHeader("producer", producerHealthChecker.check() ? "UP" : "DOWN");
            });
    }
}