package com.mserpa.javaredisrun;


import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;

public class ConnectionRedisLettuce {

    public static void main(String args[]){

        RedisClient redisClient = new RedisClient(RedisURI.create("redis://127.0.0.1:6379"));
        RedisConnection<String, String> connection = (RedisConnection<String, String>) redisClient.connect();

        System.out.println("Connected to Redis");

        connection.set("firstkey", "Hello World!");

        System.out.println(connection.get("firstkey"));

        connection.close();
        redisClient.shutdown();

    }

}
