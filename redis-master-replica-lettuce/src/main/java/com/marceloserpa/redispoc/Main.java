package com.marceloserpa.redispoc;


import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        RedisURI masterUri = RedisURI.Builder.redis("localhost", 6379).build();
        RedisClient client = RedisClient.create();

        StatefulRedisMasterReplicaConnection<String, String> connection = MasterReplica.connect(
                client,
                StringCodec.UTF8,
                masterUri);

        connection.sync().set("HELLO", "WORLD");

        System.out.println("# Wait");
        Thread.sleep(15 * 1000);

        System.out.println("# Setup Replica Read");
        connection.setReadFrom(ReadFrom.REPLICA_PREFERRED);

        var val = connection.sync().get("HELLO"); // Replica read
        System.out.println(val);

        connection.close();
        client.shutdown();

    }
}