package com.marceloserpa.spring_3_4;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person")
    public List<PersonDTO> getAll(){
        return personRepository.findAll().stream()
                .map(entity -> new PersonDTO(entity.getId(), entity.getName(), entity.getLastname()))
                .collect(Collectors.toList());
    }

    @GetMapping("/person/search/{lastname}")
    public List<PersonDTO> getPersonLastname(@PathVariable("lastname") String lastname){
        return personRepository.findByLastnameContainingIgnoreCase(lastname).stream()
                .map(entity -> new PersonDTO(entity.getId(), entity.getName(), entity.getLastname()))
                .collect(Collectors.toList());
    }


    @GetMapping("/person/{id}")
    public PersonDTO getPerson(@PathVariable("id") Long id){
        return personRepository.findById(id)
                .map(entity -> new PersonDTO(entity.getId(), entity.getName(), entity.getLastname()))
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }


    @PostMapping("/person")
    public Person save(@RequestBody PersonDTO person) {
        Person entity = new Person();
        entity.setName(person.name());
        entity.setLastname(person.lastname());
        return personRepository.save(entity);
    }

}
