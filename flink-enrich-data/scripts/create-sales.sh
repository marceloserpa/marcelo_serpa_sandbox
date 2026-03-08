#!/bin/bash

KAFKA_CONTAINER="eaec3d08fa47"
BOOTSTRAP="localhost:9092"

SALES_TOPIC="sale"

SALES=2
STORES=2
SALESMEN=4

echo "Creating fake sales..."

for ((i=1;i<=SALES;i++))
do
  STORE_ID=$(( (RANDOM % STORES) + 1 ))
  SALESMAN_ID=$(( (RANDOM % SALESMEN) + 1 ))
  TOTAL=$(awk -v min=10 -v max=1000 'BEGIN{srand(); print int(min+rand()*(max-min))}')

  JSON=$(cat <<EOF
{"id":$i,"salesman_id":$SALESMAN_ID,"store_id":$STORE_ID,"total":$TOTAL}
EOF
)

  echo "$JSON" | docker exec -i $KAFKA_CONTAINER kafka-console-producer \
    --bootstrap-server $BOOTSTRAP \
    --topic $SALES_TOPIC > /dev/null
done


echo "Fake data generated!"