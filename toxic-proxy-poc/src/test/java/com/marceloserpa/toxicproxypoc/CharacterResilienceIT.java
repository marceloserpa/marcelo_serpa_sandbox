package com.marceloserpa.toxicproxypoc;


import eu.rekawek.toxiproxy.model.toxic.Latency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;

import static eu.rekawek.toxiproxy.model.ToxicDirection.DOWNSTREAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CharacterResilienceIT extends AbstractIntegrationTest{

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add(
                "starwars.base-url",
                () -> "http://%s:%d".formatted(
                        toxiproxy.getHost(),
                        starwarsApiProxy.getProxyPort()
                )
        );
        registry.add("redis.host", redis::getHost);
        registry.add("redis.port",
                () -> redisProxy.getProxyPort());
    }

    @Autowired
    CharacterService characterClient;

    @BeforeEach
    void setup() throws IOException, InterruptedException {
        starwarsApiProxy.toxics().getAll().forEach(t -> {
            try {
                t.remove();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        mockServerClient.reset();
        redis.execInContainer("redis-cli", "FLUSHALL");
    }

    private void stubLukeSkywalker() {
        mockServerClient
            .when(org.mockserver.model.HttpRequest.request()
                    .withMethod("GET")
                    .withPath("/people/1/"))
            .respond(org.mockserver.model.HttpResponse.response()
                    .withStatusCode(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("""
                            {
                              "name":"Luke Skywalker",
                              "height":"172",
                              "mass":"77",
                              "hairColor":"blond",
                              "eyeColor":"blue",
                              "birthYear":"19BBY"
                            }
                            """));
    }

    @Test
    @DisplayName("Happy path")
    void shouldReturnCharacter() {
        stubLukeSkywalker();

        Character character = characterClient.findById(1L).get();

        assertEquals("Luke Skywalker", character.name());
    }

    @Test
    @DisplayName("External API intermittent delay issue")
    void shouldFailWithLatency() throws IOException {
        stubLukeSkywalker();

        Latency delay = starwarsApiProxy.toxics()
                .latency("delay", DOWNSTREAM, 5000);

        assertThrows(ResourceAccessException.class,
                () -> characterClient.findById(1L));

        delay.remove();

        Character character = characterClient.findById(1L).get();
        assertEquals("Luke Skywalker", character.name());
    }

    @Test
    @DisplayName("Redis failure should not affect")
    void redisFailture() throws IOException {
        stubLukeSkywalker();

        redisProxy.setConnectionCut(true);

        Character character = characterClient.findById(1L).get();
        assertEquals("Luke Skywalker", character.name());
    }

}
