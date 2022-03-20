package com.marceloserpa.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// TODO change it ListCrudRepo
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}