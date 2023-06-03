package com.marceloserpa.springreactortomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableWebFlux
@EnableR2dbcRepositories(basePackages = "com.marceloserpa.springreactortomvc.impl")

public class Application {

/**
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		return initializer;
	}
	**/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
