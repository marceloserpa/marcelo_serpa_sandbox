package com.marceloserpa.multitenantoutbox.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean(name = "datasource01")
    @ConfigurationProperties("spring.database01-datasource")
    public DataSource datasource01() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "datasource02")
    @ConfigurationProperties("spring.database02-datasource")
    public DataSource datasource02() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(TenantDatabase.DATABASE01, datasource01());
        targetDataSources.put(TenantDatabase.DATABASE02, datasource02());

        TenantRoutingDataSource multiRoutingDataSource = new TenantRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(datasource01());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }
}


