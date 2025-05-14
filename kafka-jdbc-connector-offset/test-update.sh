#!/bin/bash

echo "Updating the record..."
docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"UPDATE person SET lastname = 'Hello' WHERE name = 'John';\""

echo "Test completed. Check Kafka topic pg-person for the changes."
