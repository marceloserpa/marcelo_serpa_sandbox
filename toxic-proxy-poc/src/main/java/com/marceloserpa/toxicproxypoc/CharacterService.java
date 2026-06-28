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

    public CharacterService(RestClient restClient,
                            RedisTemplate<String, String> redisTemplate,
                            ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public Optional<Character> findById(Long id) {
        String key = "starwars:people:" + id;
        Optional<Character> cached = getCharacterFromCache(key);
        if (cached.isPresent()) return cached;

        IO.println("Not in cache ...");
        Character character = restClient.get().uri("people/"+id+"/")
                .retrieve()
                .body(Character.class);
        if(character == null) {
            return Optional.empty();
        }
        updateCharacterCache(id, key, character);

        return Optional.of(character);
    }

    private void updateCharacterCache(Long id, String key, Character character) {
        try {
            redisTemplate.opsForValue().set(key,
                    objectMapper.writeValueAsString(character),
                    Duration.ofSeconds(30));
            IO.println("Character id=" + id + " cached.");
        } catch (Exception exception) {
            IO.println("Failure to connect on Redis.... Skip update cache.");
        }

    }

    private Optional<Character> getCharacterFromCache(String key) {
        String cached = null;
        try{
            cached = redisTemplate.opsForValue().get(key);
        } catch (Exception exception) {
            IO.println("Failure to connect on Redis....");
            return Optional.empty();
        }

        if(cached != null) {
            return Optional.of(objectMapper.readValue(cached, Character.class));
        }
        return Optional.empty();
    }

}
