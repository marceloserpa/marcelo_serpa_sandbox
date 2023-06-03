package com.marceloserpa.springreactortomvc.impl.blocking;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(Long id){
        return personRepository.findById(id);
    }

}
