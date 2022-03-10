package com.marceloserpa.java17webflux;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<BookEntity> getAll(){
        return bookRepository.findAll();
    }
}
