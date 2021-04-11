package com.marceloserpa.bookstore;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book mapFromEntity(BookEntity entity){
        return new Book(entity.getId(), entity.getTitle(), entity.getAuthor());
    }

    public BookEntity mapFromRequest(Book book){
        return new BookEntity(book.getId(), book.getTitle(), book.getAuthor());
    }

}
