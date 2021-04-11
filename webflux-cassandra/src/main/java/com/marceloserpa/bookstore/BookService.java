package com.marceloserpa.bookstore;

import org.springframework.data.cassandra.core.ReactiveCassandraOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private ReactiveCassandraOperations cassandra;

    public BookService(ReactiveCassandraOperations cassandra) {
        this.cassandra = cassandra;
    }

    public Mono<BookEntity> create(BookEntity bookEntity) {
        return cassandra.insert(bookEntity);
    }

    public Flux<BookEntity> getAll(){
        return cassandra.select("SELECT * FROM book", BookEntity.class);
    }



}
