package com.marceloserpa.camelkafka.poc;

import com.marceloserpa.camelkafka.poc.healthcheckers.HttpHealthCheckRoute;
import com.marceloserpa.camelkafka.poc.routes.ApplicationRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Application {


    public static void main(String[] args) throws Exception {
        Application example = new Application();
        example.boot();
    }

    public void boot() throws Exception {
        CamelContext context = new DefaultCamelContext();
      //  context.addRoutes(new ApplicationRouter());
        context.addRoutes(new HttpHealthCheckRoute());
        context.start();
    }

}
