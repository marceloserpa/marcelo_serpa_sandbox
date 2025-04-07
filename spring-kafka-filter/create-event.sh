#!/bin/bash

KAFKA_CONTAINER="springkafka_filter_kafka"
TOPIC="events"

MESSAGE="Hello from shell script! ignore-me"

docker exec -i $KAFKA_CONTAINER kafka-console-producer \
  --broker-list kafka:29092 \
  --topic $TOPIC <<< "$MESSAGE"

echo "Event sent to '$TOPIC': $MESSAGE"