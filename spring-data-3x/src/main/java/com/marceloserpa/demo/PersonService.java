package com.marceloserpa.demo;

import org.springframework.stereotype.Service;

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

    public Iterable<Person> getAll(){
        return repository.findAll();
    }
}
