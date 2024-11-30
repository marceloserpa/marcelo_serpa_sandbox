package com.marceloserpa.spring_3_4;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Repository<Person, Long> {

    Person save(Person person);

    Optional<Person> findById(Long id);

    List<Person> findAll();

    List<Person> findByLastnameContainingIgnoreCase(String lastname);
}
