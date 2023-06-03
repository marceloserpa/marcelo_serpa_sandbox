package com.marceloserpa.springreactortomvc.api;


import com.marceloserpa.springreactortomvc.impl.blocking.PersonService;
import com.marceloserpa.springreactortomvc.impl.reactive.Person;
import com.marceloserpa.springreactortomvc.impl.reactive.PersonReactiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

    private PersonReactiveService personReactiveService;
    private PersonService personService;

    public PersonController(PersonReactiveService personReactiveService, PersonService personService) {
        this.personReactiveService = personReactiveService;
        this.personService = personService;
    }

    @GetMapping("person")
    public Flux<PersonResponse> getAll(){
        return personReactiveService.getAll()
                .map(personEntity -> new PersonResponse(personEntity.id(), personEntity.name(), personEntity.lastname()));
    }

    @GetMapping("person/{id}")
    public Mono<PersonResponse> findById(@PathVariable("id") Long id){
        return Mono.fromCallable(() -> personService.findById(id).get()) // Encapsulate blocking call using fromCallable
                .map(personEntity -> new PersonResponse(personEntity.id(), personEntity.name(), personEntity.lastname()));
    }


}
