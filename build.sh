#!/bin/bash
cd ./geeklog-core
mvn clean package
cd ..
docker-compose build
docker-compose up