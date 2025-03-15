package com.marceloserpa.spring_transaction_poc.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private KafkaTemplate<Long, String> kafkaTemplate;
    private ObjectMapper objectMapper;
    private final PersonRepository repository;

    public PersonService(KafkaTemplate<Long, String> kafkaTemplate,  ObjectMapper objectMapper, PersonRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @Transactional("transactionManager")
    public Person create(Person person) {
        var personSaved = repository.save(person);

        try {
            var jsonString = objectMapper.writeValueAsString(personSaved);
            System.out.println(jsonString);
            System.out.println("WAIT");
            Thread.sleep(10 * 1000);
            sendToKafka(jsonString);
        } catch (JsonProcessingException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return personSaved;
    }

    @Transactional("kafkaTransactionManager")
    public void sendToKafka(String json) {
        System.out.println("SEND MESSAGE");
        kafkaTemplate.send("people", 30L, json);
    }

    public List<Person> getAll(){
        return repository.findAll();
    }

    public Page<Person> getAllPage(){
        PageRequest pageable = PageRequest.of(0, 5);
        return repository.findAll(pageable);
    }


}