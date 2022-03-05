#!/bin/sh

curl -X POST 127.0.0.1:8080/producer/message -d '{"text": "oi", "delay": "1"}' -H "Content-Type: application/json"