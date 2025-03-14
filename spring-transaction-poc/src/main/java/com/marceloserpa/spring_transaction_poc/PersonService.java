package com.marceloserpa.spring_transaction_poc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public List<Person> getAll(){
        return repository.findAll();
    }

    public Page<Person> getAllPage(){
        PageRequest pageable = PageRequest.of(0, 5);
        return repository.findAll(pageable);
    }


}