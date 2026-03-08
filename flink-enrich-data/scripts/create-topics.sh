#!/bin/bash

KAFKA_CONTAINER="eaec3d08fa47"
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
    --partitions 3 \
    --replication-factor 1

done

echo "Topics created successfully!"