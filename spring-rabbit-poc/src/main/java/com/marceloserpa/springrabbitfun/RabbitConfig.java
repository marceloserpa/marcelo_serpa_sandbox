package com.marceloserpa.springrabbitfun;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionNameStrategy;
import org.springframework.amqp.rabbit.connection.SimplePropertyValueConnectionNameStrategy;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {

    @Bean
    public SimplePropertyValueConnectionNameStrategy cns() {
        return new SimplePropertyValueConnectionNameStrategy("spring.application.name");
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory(ConnectionNameStrategy cns) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setConnectionNameStrategy(cns);
        return connectionFactory;
    }

}
