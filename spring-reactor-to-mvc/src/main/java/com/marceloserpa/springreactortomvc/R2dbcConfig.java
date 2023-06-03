package com.marceloserpa.springreactortomvc;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    @Override
    @Bean("r2dbcConnectionFactory")
    public ConnectionFactory connectionFactory() {
        PostgresqlConnectionConfiguration connectionConfiguration = PostgresqlConnectionConfiguration.builder()
                .applicationName("books")
                .username("marceloserpa")
                .password("123456")
                .database("books")
                .host("localhost")
                .port(5442)
                .build();
        return new PostgresqlConnectionFactory(connectionConfiguration);
    }
}
