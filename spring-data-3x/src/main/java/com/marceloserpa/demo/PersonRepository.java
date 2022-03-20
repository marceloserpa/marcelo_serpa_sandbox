package com.marceloserpa.demo;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository
        extends PagingAndSortingRepository<Person, Long>,
                ListCrudRepository<Person, Long> {

}