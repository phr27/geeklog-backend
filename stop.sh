#!/bin/bash

if [ ! -d $HOME/log ];then
    mkdir $HOME/log
fi

docker cp geeklog-app-0:/log.txt $HOME/log/$(date '+%Y%m%d-%H:%M:%S')-log.txt

docker stop geeklog-app-0
docker stop geeklog-db-0

docker rm geeklog-app-0
docker rm geeklog-db-0

docker rmi geeklog-app
docker rmi geeklog-db
