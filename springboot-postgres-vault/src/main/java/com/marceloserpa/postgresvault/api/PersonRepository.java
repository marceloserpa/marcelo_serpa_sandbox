package com.marceloserpa.postgresvault.api;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends
        CrudRepository<Person, Long> {
}
