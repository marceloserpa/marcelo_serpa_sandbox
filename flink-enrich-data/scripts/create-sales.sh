#!/bin/bash

KAFKA_CONTAINER=a2b9cdffb99a
BOOTSTRAP="localhost:9092"

SALES_TOPIC="sale"

SALES=2
SALESMEN=4

echo "Creating fake sales..."

for ((i=1;i<=SALES;i++))
do
  SALESMAN_ID=$(( (RANDOM % SALESMEN) + 1 ))
  TOTAL=$(awk -v min=10 -v max=1000 'BEGIN{srand(); print int(min+rand()*(max-min))}')

  JSON=$(cat <<EOF
{"id":$i,"salesman_id":$SALESMAN_ID,"total":$TOTAL}
EOF
)

  echo "$JSON" | docker exec -i $KAFKA_CONTAINER kafka-console-producer \
    --bootstrap-server $BOOTSTRAP \
    --topic $SALES_TOPIC > /dev/null
done


echo "Fake data generated!"