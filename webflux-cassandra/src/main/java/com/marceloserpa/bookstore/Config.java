package com.marceloserpa.bookstore;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.cql.session.DefaultBridgedReactiveSession;

import java.net.InetSocketAddress;
import java.util.Arrays;

@Configuration
public class Config {


    @Bean
    public CqlSession cqlSession() {
        InetSocketAddress host = new InetSocketAddress("127.0.0.1", 9042);
        return CqlSession.builder()
                .withLocalDatacenter("datacenter1")
                .addContactPoints(Arrays.asList(host))
                .withKeyspace("bookstore").build();
    }

    @Bean
    public ReactiveCassandraOperations reactiveCassandraOperations(CqlSession cqlSession) {
        return new ReactiveCassandraTemplate(new DefaultBridgedReactiveSession(cqlSession));
    }


}
