# Toxic Proxy PoC

## Scenarios: 

- READ Path = Application --> REDIS
- READ Path (cache miss) = Application --> REDIS --> Starwars API --> REDIS

## Stack

- Java 25
- SpringBoot
- Redis
- Toxic Proxy

## Simulating issue: 

The following code is the version implementation of CharacterService. Redis is being used only for caching and the 
application should still work if Redis not available.

The code looks fine but what happens if Redis goes down?

```java
    public Optional<Character> findById(Long id) {
        String key = "starwars:people:" + id;
        
        // naive implementation.
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
```

This test uses ToxicProxy to simulate a disruption on Redis connectivity.

```java
    @Test
    @DisplayName("Redis failure should not affect")
    void redisFailture() throws IOException {
        stubLukeSkywalker();

        redisProxy.setConnectionCut(true); // <<--- simulate a connection issue on redis

        Character character = characterClient.findById(1L).get();
        assertEquals("Luke Skywalker", character.name());
    }
```
As we can see, in case of any communication issue between the application and Redis the request will fail instead 
of calling the API directly. This test expose a serious failure in the application.

```shell
Unable to connect to Redis
org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis
	at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.translateException(LettuceConnectionFactory.java:1873)
	at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$ExceptionTranslatingConnectionProvider.getConnection(LettuceConnectionFactory.java:1804)
	at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.getNativeConnection(LettuceConnectionFactory.java:1601)
	at org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory$SharedConnection.lambda$getConnection$0(LettuceConnectionFactory.java:1581)
```