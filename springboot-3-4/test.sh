#!/usr/bin/zsh

curl -X POST localhost:8080/person \
   -H 'Content-Type: application/json' \
   -d '{"name":"marcelo","lastname":"serpa"}'