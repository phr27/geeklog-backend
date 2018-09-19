#!/bin/bash

if [ ! -d $HOME/log ];then
    mkdir $HOME/log
fi

docker cp geeklog-app-0:/log.txt $HOME/log/$(date '+%Y%m%d-%H:%M:%S')-log.txt

rm -rf ~/ftpfile/

docker stop geeklog-ftp-0
docker stop geeklog-app-0
docker stop geeklog-db-0
docker stop geeklog-nginx-0

docker rm geeklog-ftp-0
docker rm geeklog-app-0
docker rm geeklog-db-0
docker rm geeklog-nginx-0

docker rmi geeklog-ftp
docker rmi geeklog-app
docker rmi geeklog-db
docker rmi geeklog-nginx
