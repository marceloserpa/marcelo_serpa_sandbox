package com.marceloserpa;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BookResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Book> getAll() {

        return Book.listAll();
    }
}