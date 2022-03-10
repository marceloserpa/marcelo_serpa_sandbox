package com.marceloserpa.java17webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public Flux<Book> getAll(){
        return bookService.getAll().map(bookEntity -> {
            return new Book(bookEntity.id(), bookEntity.title(), bookEntity.author(), bookEntity.price());
        });
    }

}
