#!/bin/bash

echo "Updating the record..."
docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"SELECT name, lastname, subscription, created_at, updated_at, EXTRACT(EPOCH FROM updated_at) * 1000 AS updated_at_epoch_ms FROM person;\""