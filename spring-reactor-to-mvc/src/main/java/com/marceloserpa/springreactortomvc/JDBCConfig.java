package com.marceloserpa.springreactortomvc;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;



@Configuration
@EnableJdbcRepositories(
       // repositoryFactoryBeanClass = CustomJdbcRepositoryFactoryBean.class,
        basePackages = "com.marceloserpa.springreactortomvc.impl.blocking",
        excludeFilters =
                {@ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE, classes = {ReactiveCrudRepository.class})})



public class JDBCConfig extends AbstractJdbcConfiguration {

    @Bean("jdbcDataSource")
    @Primary
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5442/books");
        dataSourceBuilder.username("marceloserpa");
        dataSourceBuilder.password("123456");
        return dataSourceBuilder.build();

    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("jdbcMappingContext")
    @Primary
    public JdbcMappingContext jdbcMappingContext() {
        return new JdbcMappingContext();
    }

}
