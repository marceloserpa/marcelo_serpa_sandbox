#!/bin/bash

curl -X POST \
    "http://localhost:8080/realms/marcelocorp/protocol/openid-connect/token" \
    -H "Content-Type: application/x-www-form-urlencoded" \
    -d "client_id=movies-app" \
    -d "grant_type=password" \
    -d "username=marcelo" \
    -d "password=marcelo"