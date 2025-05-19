#!/bin/bash

EXECUTION_TIMES=$1
SLEEP_TIME=10

UUID=$(uuidgen)

echo "Starting test: Inserting $EXECUTION_TIMES records with $SLEEP_TIME-second intervals..."

for ((i = 1; i <= EXECUTION_TIMES; i++)); do
    echo "Insert #$i: Adding record for Person $i..."
    docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"INSERT INTO facts (event_content, entity) VALUES ('$UUID - ping $i', 'clients');\""
    
    if [ $i -lt $EXECUTION_TIMES ]; then
        echo "Waiting $SLEEP_TIME seconds before next insert..."
        sleep $SLEEP_TIME
    fi
done

echo "Test completed. $EXECUTION_TIMES records have been inserted."
echo "Check Kafka topic pg-person for the changes."