package com.marceloserpa.spring_transaction_poc.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return personService.getAll();
    }

    @GetMapping("/page")
    public Page<Person> getAllPage() {
        return personService.getAllPage();
    }


}
