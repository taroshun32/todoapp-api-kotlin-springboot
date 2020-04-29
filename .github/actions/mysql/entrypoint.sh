#!/bin/sh

docker_run="docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=todo -e MYSQL_ROOT_HOST='%' -p 3306:3306 mysql/mysql-server:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci"

sh -c "$docker_run"
