package com.marceloserpa.postgresvault;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import javax.sql.DataSource;



@Configuration
@EnableJdbcRepositories
public class DatabaseConfig {


    @Bean
    public DataSource dataSource() {

        var credentials = accessCredentials();
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5442/books");
        ds.setUsername(credentials.getUsername());
        ds.setPassword(credentials.getPassword());
        return ds;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    VaultOperations vaultOperations;

    public Credentials accessCredentials() {

        VaultResponse response = vaultOperations.read("secret/data/db");
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(response.getData().get("data"), Credentials.class);
    }

}
