package com.mserpa.statecontrol.app;

import com.google.common.base.Stopwatch;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.rx.RedisReactiveCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutionException;


public class Application {

    public static final String REDIS_HOST = "redis://@localhost:6379/0";

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create(REDIS_HOST);

        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("event", "Redis!");

        System.out.println("using sync command");
        Stopwatch measureSync = Stopwatch.createStarted();
        System.out.println(syncCommands.get("event:72966"));;
        measureSync.stop();
        System.out.println(measureSync);

        syncCommands.close();
        connection.close();

        // using asyncCommands command with future
        connection = redisClient.connect();
        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        System.out.println("using asyncCommands command with future");

        Stopwatch measureAsync = Stopwatch.createStarted();
        RedisFuture<String> future = asyncCommands.get("event:72966");
        while(!future.isDone()){ }
        try {
            System.out.println("Done " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        measureAsync.stop();
        System.out.println(measureAsync);
        asyncCommands.close();
        connection.close();

        // using reactive command
        connection = redisClient.connect();
        RedisReactiveCommands<String, String> reactiveCommands = connection.reactive();

        Stopwatch measure = Stopwatch.createStarted();
        System.out.println("using reactive command");
        reactiveCommands.get("event:72966")
            .subscribeOn(Schedulers.immediate())
            .subscribe(value -> {
                System.out.println(value);
                measure.stop();
                System.out.println(measure);
            });
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisClient.shutdown();
    }

}
