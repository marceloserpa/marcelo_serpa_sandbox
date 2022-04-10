package com.marceloserpa.reactivepgpoc;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;


public class Fruit {

    public Long id;

    public String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Multi<Fruit> findAll(PgPool client) {
        return client.query("SELECT id, name FROM fruits ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Fruit::from);
    }

    private static Fruit from(Row row) {
        return new Fruit(row.getLong("id"), row.getString("name"));
    }

}