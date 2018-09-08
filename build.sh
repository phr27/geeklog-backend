#!/bin/bash
cd ./geeklog-core
mvn clean package -Dmaven.test.skip=true
cd ..
docker-compose build
docker-compose up