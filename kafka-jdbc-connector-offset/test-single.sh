#!/bin/bash

echo "Inserting"
docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"INSERT INTO person (name, lastname) VALUES ('Person Hello', 'Lastname Hello');\""
