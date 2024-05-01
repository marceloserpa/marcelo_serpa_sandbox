#!/bin/sh

curl -X POST --location "http://localhost:8083/connectors" -H "Content-Type: application/json" -H "Accept: application/json" -d @database01-source-connector.json



curl -X POST --location "http://localhost:8083/connectors" -H "Content-Type: application/json" -H "Accept: application/json" -d @database02-source-connector.json