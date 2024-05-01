#!/bin/bash

connectors=("database01-source-connector" "database02-source-connector")

for connector in ${connectors[@]}; do
    echo ">> Starting $connector setup"
    curl -X POST --location "http://localhost:8083/connectors" \
        -H "Content-Type: application/json" \
        -H "Accept: application/json" \
        -d @connectors/$connector.json

    sleep 2
    echo ">> Completed $connector setup"
done
