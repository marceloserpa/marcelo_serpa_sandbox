package com.mserpa.statecontrol.app;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class Application {

    public static final String REDIS_HOST = "redis://marcelo@localhost:6379/0";

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create(REDIS_HOST);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("event", "Redis!");
        syncCommands.set("event", "Redis!!");
        syncCommands.set("event", "Redis!!!");
        syncCommands.set("event", "Redis!!!!");
        syncCommands.set("event", "Redis!!!!!");
        System.out.println(syncCommands.get("event"));;

        connection.close();
        redisClient.shutdown();
    }

}
