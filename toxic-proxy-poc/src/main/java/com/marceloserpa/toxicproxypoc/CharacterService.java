package com.marceloserpa.toxicproxypoc;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class CharacterService {

    public Optional<Character> findById(Long id) {
        RestClient restClient = RestClient.create();
        var result = restClient.get().uri("https://swapi.bry.com.br/api/people/"+id+"/")
                .retrieve()
                .body(Character.class);

        IO.println(result);
        return Optional.ofNullable(result);
    }

}
