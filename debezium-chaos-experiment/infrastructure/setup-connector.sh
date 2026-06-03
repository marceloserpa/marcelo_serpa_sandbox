#!/bin/bash


echo ">> Starting setup"
curl -X POST --location "http://localhost:8083/connectors" \
    -H "Content-Type: application/json" \
    -H "Accept: application/json" \
    -d @connectors/database-source-connector.json

sleep 2

echo " >>>> Completed!! <<< "