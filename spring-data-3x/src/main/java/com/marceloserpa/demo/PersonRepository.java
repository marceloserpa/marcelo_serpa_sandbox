package com.marceloserpa.demo;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends ListCrudRepository<Person, Long> {

}