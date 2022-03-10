package com.marceloserpa.java17webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Flux<Book> getAll(){
        return bookService.getAll().map(bookEntity -> {
            return new Book(bookEntity.id(), bookEntity.title(), bookEntity.author(), bookEntity.price());
        });
    }

    @PostMapping
    public Mono<Book> save(@RequestBody Book book){
        var bookEntity = new BookEntity(book.id(), book.title(), book.author(), book.price());
        return bookService.save(bookEntity).map(entity -> {
            return new Book(entity.id(), entity.title(), entity.author(), entity.price());
        });
    }

}
