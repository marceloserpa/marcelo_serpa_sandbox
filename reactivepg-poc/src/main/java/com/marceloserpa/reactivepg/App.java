package com.marceloserpa.reactivepg;

import io.reactiverse.pgclient.PgClient;
import io.reactiverse.pgclient.PgIterator;
import io.reactiverse.pgclient.PgPool;
import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.pgclient.PgRowSet;
import io.reactiverse.pgclient.Row;


public class App {

	public static void main(String[] args) throws InterruptedException {
		PgPoolOptions options = new PgPoolOptions()
				  .setPort(5432)
				  .setHost("localhost")
				  .setDatabase("postgres")
				  .setUser("postgres")
				  .setPassword("docker")
				  .setMaxSize(5);
		
		PgPool client = PgClient.pool(options);
		
		// A simple query
		client.query("SELECT * FROM books", ar -> {
		  if (ar.succeeded()) {
		    PgRowSet rowSet = ar.result();
		    PgIterator iterator = rowSet.iterator();
		    
		    Row row = null;
		    while((row = iterator.next()) != null) {
		    	System.out.println(row.getInteger("id"));
		    	System.out.println(row.getString("title"));
		    	System.out.println("----");
		    }
		    
		    System.out.println("Got " + rowSet.size() + " rows ");
		  } else {
		    System.out.println("Failure: " + ar.cause().getMessage());
		  }

		  // Now close the pool
		  client.close();
		});
		
		
		
	}
	
}
