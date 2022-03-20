package com.marceloserpa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person save(@RequestBody Person person) {
        return personService.create(person);
    }

    @GetMapping
    public List<Person> getALl() {
        return StreamSupport.stream(personService.getAll().spliterator(), false)
                .collect(Collectors.toList());
    }


}
