#!/bin/bash

KAFKA_CONTAINER=a2b9cdffb99a
BOOTSTRAP_SERVER="localhost:9092"

TOPICS=("salesman" "sale" "store" "sales-enriched")

for TOPIC in "${TOPICS[@]}"
do
  echo "Creating topic: $TOPIC"

  docker exec -it $KAFKA_CONTAINER kafka-topics \
    --create \
    --if-not-exists \
    --topic $TOPIC \
    --bootstrap-server $BOOTSTRAP_SERVER \
    --partitions 1 \
    --replication-factor 1

done

echo "Topics created successfully!"