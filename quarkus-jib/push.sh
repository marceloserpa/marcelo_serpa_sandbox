#!/bin/sh   

./mvnw clean package \
    -DskipTests \
    -Dquarkus.container-image.push=true \
    -Dquarkus.container-image.registry=registry-1.docker.io \
    -Dquarkus.container-image.username=$DOCKER_HUB_USER \
    -Dquarkus.container-image.password=$DOCKER_HUB_ACCESS_TOKEN \
    -Dquarkus.container-image.group=marceloserpa \
    -Dquarkus.container-image.name=quarkus-jib