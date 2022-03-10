package com.marceloserpa;

import com.marceloserpa.entity.BookEntity;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/books")
public class BookResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll() {
        return BookEntity.listAll().stream().map(entity -> {
            BookEntity bookEntity = (BookEntity) entity;
            Book book = new Book();
            book.setId(bookEntity.id);
            book.setTitle(bookEntity.title);
            return book;
        }).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void save(Book book) {
        BookEntity entity = new BookEntity();
        entity.title = book.getTitle();
        entity.persist();
    }
}