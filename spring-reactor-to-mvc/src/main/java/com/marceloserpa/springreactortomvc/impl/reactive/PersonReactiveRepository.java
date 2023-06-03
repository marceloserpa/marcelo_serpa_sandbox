package com.marceloserpa.springreactortomvc.impl.reactive;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PersonReactiveRepository extends
        R2dbcRepository<Person, Long> {
}
