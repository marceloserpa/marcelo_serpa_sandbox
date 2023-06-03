#!/bin/bash

docker-compose exec vault_dev vault secrets enable -version=2 kv

docker-compose exec vault_dev vault kv put secret/db password=123456 username=marceloserpa
