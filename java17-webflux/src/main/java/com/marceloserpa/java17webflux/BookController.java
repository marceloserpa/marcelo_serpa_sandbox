package com.marceloserpa.java17webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class BookController {

    @GetMapping("/books")
    public Flux<Book> getAll(){
        return Flux.just(new Book("The Shining"));
    }

}
