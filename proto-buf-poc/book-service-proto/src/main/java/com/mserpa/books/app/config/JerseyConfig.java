package com.mserpa.books.app.config;


import com.mserpa.books.app.endpoint.BookController;
import com.pakulov.jersey.protobuf.ProtobufFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(ProtobufFeature.class);
        register(BookController.class);
    }

}