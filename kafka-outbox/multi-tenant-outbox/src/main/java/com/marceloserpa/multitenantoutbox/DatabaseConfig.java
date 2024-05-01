package com.marceloserpa.multitenantoutbox;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
public class DatabaseConfig {
    @Primary
    @Bean(name = "datasource01")
    @ConfigurationProperties("spring.database01-datasource")
    public DataSource datasource01() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }



}


