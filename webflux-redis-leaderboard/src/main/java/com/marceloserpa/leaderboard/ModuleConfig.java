package com.marceloserpa.leaderboard;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisSortedSetAsyncCommands;
import io.lettuce.core.api.reactive.RedisListReactiveCommands;
import io.lettuce.core.api.reactive.RedisSortedSetReactiveCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class ModuleConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.timeout-seconds}")
    private int redisTimeoutSeconds;

    @Bean
    public RedisSortedSetReactiveCommands<String, String> redisStringReactiveCommands() {
        RedisURI redisURI = new RedisURI(redisHost, redisPort, Duration.ofSeconds(redisTimeoutSeconds));
        RedisClient client = RedisClient.create(redisURI);
        RedisSortedSetReactiveCommands<String, String> commands = client.connect().reactive();
        return commands;
    }


}
