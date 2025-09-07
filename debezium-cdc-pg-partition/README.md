# Debezium CDC with Postgres and Partitioned Table, publishing to RabbitMQ (Docker Compose)

This project sets up a **Change Data Capture (CDC)** pipeline using:
- **PostgreSQL** (with `wal_level=logical` for CDC)
- **RabbitMQ** (as the message broker)
- **Debezium Server** (to capture DB changes and publish to RabbitMQ)

## Services

| Service     | Purpose                         | Port                  |
|:------------|:---------------------------------|:----------------------|
| Postgres    | Source database for CDC          | 5442 (host) -> 5432    |
| RabbitMQ    | Message broker                   | 5672 (AMQP), 15672 (UI)|
| Debezium    | CDC Server publishing to RabbitMQ| 8080 (Debezium HTTP)   |


## Partitions

The table measurement in partitioned in two range 2025-01-01 to 2025-05-31 and 2025-06-01 to 2025-12-31.

## How to run

Start all containers

```
docker-compose up -d
```

Insert 10 rows into database.

```
./test.sh
```
