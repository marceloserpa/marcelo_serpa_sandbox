package com.marceloserpa.springreactortomvc.api;


import com.marceloserpa.springreactortomvc.impl.Person;
import com.marceloserpa.springreactortomvc.impl.PersonReactiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

    private PersonReactiveService personService;

    public PersonController(PersonReactiveService personService) {
        this.personService = personService;
    }

    @GetMapping("person")
    public Flux<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping("person/{id}")
    public Mono<Person> findById(@PathVariable("id") Long id){
        return personService.findById(id);
    }


}
