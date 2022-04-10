package com.marceloserpa.redisfun;

import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;

import io.vertx.mutiny.redis.client.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class IncrementService {

    @Inject
    ReactiveRedisClient reactiveRedisClient;

    Uni<Void> del(String key) {
        return reactiveRedisClient.del(Arrays.asList(key))
                .map(response -> null);
    }

    Uni<String> get(String key) {
        return reactiveRedisClient.get(key)
                .map(value -> value.toString());
    }

    Uni<Void> set(String key, Integer value) {
        return reactiveRedisClient.set(Arrays.asList(key, value.toString()))
                .map(response -> null);
    }

    Uni<Void> increment(String key, Integer incrementBy) {
        return reactiveRedisClient.incrby(key, incrementBy.toString())
                .map(response -> null);
    }

    Uni<List<String>> keys() {
        return reactiveRedisClient
                .keys("*")
                .map(response -> {
                    List<String> result = new ArrayList<>();
                    for (Response r : response) {
                        result.add(r.toString());
                    }
                    return result;
                });
    }
}