package com.marceloserpa.reactivepgpoc;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("fruits")
public class FruitResource {

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @GET
    public Multi<Fruit> getAll(){
        return Fruit.findAll(client);
    }


    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS fruits").execute()
                .flatMap(r -> client.query("CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL)").execute())
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Orange')").execute())
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Pear')").execute())
                .flatMap(r -> client.query("INSERT INTO fruits (name) VALUES ('Apple')").execute())
                .await().indefinitely();
    }



}
