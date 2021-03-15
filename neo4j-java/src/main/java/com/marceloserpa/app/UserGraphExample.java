package com.marceloserpa.app;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.util.ArrayList;
import java.util.List;

public class UserGraphExample implements AutoCloseable {
    private final Driver driver;

    public UserGraphExample(String uri, String user, String password ) {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void createSocialNetworking() {

        List<String> queries = new ArrayList<>();
        queries.add("CREATE (n:UserPoc {name: 'Bob', title: 'Developer'});");
        queries.add("CREATE (n:UserPoc {name: 'Chris', title: 'Developer'});");
        queries.add("CREATE (n:UserPoc {name: 'John', title: 'Architect'});");
        queries.add("MATCH (a:UserPoc), (b:UserPoc) WHERE a.name = 'Bob' AND b.name = 'Chris' CREATE (a)-[r:FOLLOW]->(b) RETURN type(r);");
        queries.add("MATCH (a:UserPoc), (b:UserPoc) WHERE a.name = 'Bob' AND b.name = 'John' CREATE (a)-[r:FOLLOW]->(b) RETURN type(r);");
        queries.add("MATCH (a:UserPoc), (b:UserPoc) WHERE a.name = 'John' AND b.name = 'Bob' CREATE (a)-[r:FOLLOW]->(b) RETURN type(r);");

        for(String query : queries) {
            try ( Session session = driver.session() ) {
                String result = session.writeTransaction(tx -> {
                    tx.run(query);
                    return "ok";
                });
                System.out.println(result);
        }
        }
    }

    public static void main( String... args ) throws Exception {
        try ( UserGraphExample greeter = new UserGraphExample( "bolt://localhost:7687", "neo4j", "your_password" ) ) {
            greeter.createSocialNetworking();
        }
    }


}
