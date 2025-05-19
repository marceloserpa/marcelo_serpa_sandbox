#!/bin/bash

echo "Updating the record..."
docker exec -i jdbc-connector-poc-db bash -c "psql -U kafka -d products -c \"SELECT id, event_content, entity, created_at, EXTRACT(EPOCH FROM created_at) * 1000 AS created_at_epoch_ms FROM facts;\""