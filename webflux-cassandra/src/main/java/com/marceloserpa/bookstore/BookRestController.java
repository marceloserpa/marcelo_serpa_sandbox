package com.marceloserpa.bookstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookRestController {

    private BookService bookService;
    private BookMapper mapper;

    public BookRestController(BookService bookService, BookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping("books")
    public Mono<Book> create(@RequestBody Book request){
        return Mono.just(request)
                .map(mapper::mapFromRequest)
                .flatMap(book -> bookService.create(book))
                .map(mapper::mapFromEntity);
    }

    @GetMapping("books")
    public Flux<Book> getAll(){
        return bookService.getAll()
                .map(mapper::mapFromEntity);
    }


}
