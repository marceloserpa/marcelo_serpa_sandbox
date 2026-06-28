package com.marceloserpa.toxicproxypoc;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Optional;

@Service
public class CharacterService {

    private final RestClient restClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public CharacterService(RestClient restClient, RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<Character> findById(Long id) {
        String key = "starwars:people:" + id;
        String cached = redisTemplate.opsForValue().get(key);
        if(cached != null) {
            return Optional.of(objectMapper.readValue(cached, Character.class));
        }

        IO.println("Not in cache ...");
        Character character = restClient.get().uri("people/"+id+"/")
                .retrieve()
                .body(Character.class);
        if(character == null) {
            return Optional.empty();
        }

        redisTemplate.opsForValue().set(key,
                objectMapper.writeValueAsString(character),
                Duration.ofSeconds(30));
        IO.println("Character id=" + id + " cached.");

        return Optional.of(character);
    }

}
