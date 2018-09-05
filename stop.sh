#!/bin/bash
docker stop geeklog-app-0
docker stop geeklog-db-0

docker rm geeklog-app-0
docker rm geeklog-db-0

docker rmi geeklog-app
docker rmi geeklog-db
