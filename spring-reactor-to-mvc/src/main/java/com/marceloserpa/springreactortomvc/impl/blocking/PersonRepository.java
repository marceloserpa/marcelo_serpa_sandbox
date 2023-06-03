package com.marceloserpa.springreactortomvc.impl.blocking;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
