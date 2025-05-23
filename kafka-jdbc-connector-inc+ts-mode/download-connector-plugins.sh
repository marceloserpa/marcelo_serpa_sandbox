#!/bin/bash

echo "Setting up Confluent JDBC connector..."

mkdir -p connect-plugins

JDBC_CONNECTOR_JAR="connect-plugins/kafka-connect-jdbc-10.7.4.jar"
if [ -f "$JDBC_CONNECTOR_JAR" ]; then
    echo "JDBC connector JAR already exists, skipping download"
else
    echo "Downloading Confluent JDBC connector JAR..."
    wget -q -P connect-plugins/ https://packages.confluent.io/maven/io/confluent/kafka-connect-jdbc/10.7.4/kafka-connect-jdbc-10.7.4.jar

    if [ $? -eq 0 ]; then
        echo "Successfully downloaded JDBC connector JAR"
    else
        echo "Failed to download JDBC connector JAR"
        exit 1
    fi
fi

PG_DRIVER_JAR="connect-plugins/postgresql-42.7.3.jar"
if [ -f "$PG_DRIVER_JAR" ]; then
    echo "PostgreSQL JDBC driver already exists, skipping download"
else
    echo "Downloading PostgreSQL JDBC driver..."
    wget -q -P connect-plugins/ https://jdbc.postgresql.org/download/postgresql-42.7.3.jar

    if [ $? -eq 0 ]; then
        echo "Successfully downloaded PostgreSQL JDBC driver"
    else
        echo "Failed to download PostgreSQL JDBC driver"
        exit 1
    fi
fi

echo "Restarting Kafka Connect container..."
docker restart jdbc-connector-poc-connect

echo "Waiting for Kafka Connect to restart (10 seconds)..."
sleep 10

echo "Checking if JDBC connector plugin is available..."
CONNECTOR_PLUGINS=$(curl -s http://localhost:8083/connector-plugins)

if [[ $CONNECTOR_PLUGINS == *"io.confluent.connect.jdbc.JdbcSourceConnector"* ]]; then
    echo "JDBC connector plugin is available!"
    echo "You can now run ./setup-connector.sh to create the connector"
else
    echo "JDBC connector plugin is not available. Something went wrong."
    exit 1
fi

echo "Setup complete!"
