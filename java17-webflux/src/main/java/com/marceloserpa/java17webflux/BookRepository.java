package com.marceloserpa.java17webflux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<BookEntity, Integer> {

}