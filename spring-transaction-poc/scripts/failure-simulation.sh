#!/bin/bash

suffix=$RANDOM

echo "Simulating Kafka failure while Spring transaction is open with Postgres and Kafka"

echo "Perform request to create a new user $suffix"

curl --header "Content-Type: application/json" \
  --request POST \
  --data "{\"name\":\"Marcelo $suffix\",\"lastname\":\"xyz $suffix\"}" \
  http://localhost:8080/person > /dev/null 2>&1 &

sleep 7

echo "Stopping Kafka container"
docker-compose stop kafka

sleep 5

echo "Killing Java APP"
pid=$(ps -ef | grep spring_transaction_poc | awk '{print $2}' | head -n 1)

kill -9 $pid

echo "Starting Kafka container"
docker-compose start kafka