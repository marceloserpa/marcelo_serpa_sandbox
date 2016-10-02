package com.mserpa.books.app.endpoint;

import com.mserpa.books.app.generatedproto.Protos;
import com.pakulov.jersey.protobuf.internal.MediaTypeExt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/books")
public class BookController {

    @GET
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public Protos.MessageCard test(){
        return Protos.MessageCard.newBuilder()
                .setUser("User")
                .setText("sss")
                .build();
    }


}
