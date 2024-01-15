#!/usr/bin/env bash

DB_DOCKER_NAME=ecohub-db
NETWORK_NAME=ecohub-db-network

echo "Stopping & removing Postgresql DB docker container if any"
docker stop $DB_DOCKER_NAME
docker rm $DB_DOCKER_NAME

# Delete network
echo "Deleting docker network '$NETWORK_NAME' if any"
docker network rm ${NETWORK_NAME} || true

echo "Creating docker network '$NETWORK_NAME'"
docker network create -d bridge ${NETWORK_NAME} || exit 1

##############################################################################################
# Setting --memory and --memory-swap with the same value
# disable SWAP completely (https://docs.docker.com/config/containers/resource_constraints/#prevent-a-container-from-using-swap)
##############################################################################################
docker run \
  -v "$(pwd)/init.sql:/docker-entrypoint-initdb.d/init.sql" \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  --network ${NETWORK_NAME} \
  -p 5432:5432 \
  -d --name ${DB_DOCKER_NAME} \
  --cpus=1 \
  --memory=1g --memory-swap=1g --memory-reservation=512m \
  postgres:14.4
