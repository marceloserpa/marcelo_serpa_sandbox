package com.marceloserpa.springreactortomvc.impl;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonReactiveService {

    private PersonReactiveRepository personRepository;

    public PersonReactiveService(PersonReactiveRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> getAll(){
        return personRepository.findAll();
    }

    public Mono<Person> findById(Long id) {
        return personRepository.findById(id);
    }
}
