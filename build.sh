#!/bin/bash
cd ./geeklog-core
mvn clean package -Dmaven.test.skip=true
cd ..
if [ -e ~/ftpfile ];then
    rm -rf ~/ftpfile
fi
mkdir -p ~/ftpfile
docker-compose build
docker-compose up