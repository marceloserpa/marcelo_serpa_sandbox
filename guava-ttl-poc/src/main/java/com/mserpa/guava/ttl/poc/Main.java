package com.mserpa.guava.ttl.poc;

import com.google.common.cache.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> removed = new LinkedList<>();

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    public void onRemoval(RemovalNotification<String, String> removal) {
                        if(removal.wasEvicted()){
                            removed.add(removal.getValue());
                        }
                    }
                })
                .build();

        cache.put("key-1", "value-a");
        cache.put("key-2", "value-b");


        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);

        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);

        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);

        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);

        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);

        cache.put("key-1", "value-c");

        System.out.println(cache.getIfPresent("key-1"));
        Thread.sleep(300L);


        Thread.sleep(1000L);

        Thread.sleep(2000L);
        System.out.println("\n++++++\nremoved values");
        for(String k : removed){
            System.out.println(k);
        }
        System.out.println("\n\n");

    }

}
