package com.marceloserpa.fun.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        var person = new PersonDTO("Oi", 30, 100, LocalDate.of(2022, 1, 15));
        var json = objectMapper.writeValueAsString(person);
        System.out.println(json);

        PersonDTO personFromJson = objectMapper.readValue(json, PersonDTO.class);
        System.out.println(personFromJson);
    }
}
