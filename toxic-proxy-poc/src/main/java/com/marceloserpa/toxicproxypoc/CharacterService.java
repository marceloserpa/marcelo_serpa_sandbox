package com.marceloserpa.toxicproxypoc;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CharacterService {

    private RestClient restClient;
    private RedisTemplate<String, String> redisTemplate;
    private ObjectMapper objectMapper;

    public CharacterService(RestClient restClient, RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<Character> findById(Long id) {

        String characterCache = redisTemplate.opsForValue().get("starwars:people:" + id);

        if(characterCache == null) {
            IO.println("Not in cache ...");
            Character character = restClient.get().uri("people/"+id+"/")
                    .retrieve()
                    .body(Character.class);
            IO.println(character);
            if(character != null) {
                var value = objectMapper.writeValueAsString(character);
                redisTemplate.opsForValue().set("starwars:people:" + id, value, Expiration.from(Duration.of(30, ChronoUnit.SECONDS)));
                IO.println("Character id=" + id + " cached.");
                return Optional.of(character);
            }
        }
        var character = objectMapper.readValue(characterCache, Character.class);
        return Optional.ofNullable(character);
    }

}
