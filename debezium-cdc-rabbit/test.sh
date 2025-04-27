#!/bin/bash

docker exec -i debezium-cdc-rabbit-db bash -c "psql -U postgres -d debezium-cdc-rabbit-db -c \"INSERT INTO person (name, lastname) VALUES ('John', 'Doe');\""