package com.marceloserpa.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person person) {
        Person saved = repository.save(person);
        return saved;
    }

    public List<Person> getAll(){
        return repository.findAll();
    }
}
