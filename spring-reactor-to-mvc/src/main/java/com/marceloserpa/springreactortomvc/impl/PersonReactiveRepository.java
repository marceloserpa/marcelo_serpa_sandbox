package com.marceloserpa.springreactortomvc.impl;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface PersonReactiveRepository extends
        ReactiveCrudRepository<Person, Long> {
}
