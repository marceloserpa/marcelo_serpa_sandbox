#!/bin/bash

echo "Starting test: Inserting 10 records with 10-second intervals..."

for i in {1..10}
do
    echo "Insert #$i: Adding record for Person $i..."
    docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"INSERT INTO person (name, lastname) VALUES ('Person $i', 'Lastname $i');\""
    
    if [ $i -lt 10 ]; then
        echo "Waiting 10 seconds before next insert..."
        sleep 10
    fi
done

echo "Test completed. 10 records have been inserted."
echo "Check Kafka topic pg-person for the changes."
