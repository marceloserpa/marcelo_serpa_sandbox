#!/bin/bash

KAFKA_CONTAINER="eaec3d08fa47"
BOOTSTRAP="localhost:9092"

SALESMAN_TOPIC="salesman"
STORE_TOPIC="store"

STORES=2
SALESMEN=4

echo "Creating fake stores..."

for ((i=1;i<=STORES;i++))
do
  CITY=("New York" "San Francisco")
  STATE=("NY" "CA")

  IDX=$((RANDOM % 2))

  JSON=$(cat <<EOF
{"id":$i,"name":"Store $i","city":"${CITY[$IDX]}","state":"${STATE[$IDX]}"}
EOF
)

  echo "$JSON" | docker exec -i $KAFKA_CONTAINER kafka-console-producer \
    --bootstrap-server $BOOTSTRAP \
    --topic $STORE_TOPIC > /dev/null
done


echo "Creating fake salesmen..."

for ((i=1;i<=SALESMEN;i++))
do
  STORE_ID=$(( (RANDOM % STORES) + 1 ))

  JSON=$(cat <<EOF
{"id":$i,"name":"Salesman $i","store_id":$STORE_ID}
EOF
)

  echo "$JSON" | docker exec -i $KAFKA_CONTAINER kafka-console-producer \
    --bootstrap-server $BOOTSTRAP \
    --topic $SALESMAN_TOPIC > /dev/null
done

echo "Fake data generated!"