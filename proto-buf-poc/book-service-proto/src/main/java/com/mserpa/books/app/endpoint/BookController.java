package com.mserpa.books.app.endpoint;

import com.mserpa.books.app.generatedproto.Protos;
import com.pakulov.jersey.protobuf.internal.MediaTypeExt;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/books")
public class BookController {

    private static List<Protos.MessageCard> messages = new ArrayList<>();

    @GET
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public Protos.MessageList getAllBooks(){
        Protos.MessageCard messageCard = Protos.MessageCard.newBuilder()
                .setUser("User")
                .setText("sss")
                .build();
        messages.add(messageCard);
        return Protos.MessageList.newBuilder().addAllMessages(messages).build();
    }

    @POST
    @Produces(MediaTypeExt.APPLICATION_PROTOBUF)
    public void create(Protos.MessageCard messageCard){
        System.out.println("BookController -CREATE ========================");
        System.out.println(messageCard.getUser());
        System.out.println(messageCard.getText());
        messages.add(messageCard);
        System.out.println(messages.size());
        System.out.println("END--");
    }

}
