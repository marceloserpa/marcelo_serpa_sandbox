# Toxic Proxy PoC


## Scenarios: 

- Write path = application --> HTTP API? --> Postgres
- READ Path = application --> REDIS --> Postgres
- READ Path (cache miss) = application --> REDIS --> Postgres --> REDIS

** PG Read-replica??


## Stack

- Java 25
- SpringBoot
- Postgres
- Redis
- Toxic Proxy