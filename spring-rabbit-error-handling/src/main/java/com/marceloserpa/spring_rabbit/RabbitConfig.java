package com.marceloserpa.spring_rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("orders.dlx.exchange");
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("orders.exchange");
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable("orders.dlx.queue").build();
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable("orders.queue")
                .withArgument("x-dead-letter-exchange", "orders.dlx.exchange")
                .withArgument("x-dead-letter-routing-key", "orders.dlx.rk")
                .build();
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("orders.dlx.rk");
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("orders.queue");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
