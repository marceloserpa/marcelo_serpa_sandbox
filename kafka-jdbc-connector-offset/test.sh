#!/bin/bash

docker exec -i database-poc bash -c "psql -U kafka -d products -c \"INSERT INTO person (name, lastname) VALUES ('John', 'Doe');\""