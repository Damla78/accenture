docker pull mysql
docker pull openjdk:8

    docker run --detach --name accenture-mysql-2 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=accenturedb -e MYSQL_USER=sa -e MYSQL_PASSWORD=sa -d mysql
FileName
docker exec -i e0 mysql -uroot -proot < src/main/resources/schema.sql
docker run --net=host   test-accenture-spring


docker run --name accenture-mysql -e 
MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accenture-db -e 
MYSQL_USER=sa -e MYSQL_PASSWORD=sa -d mysql
FIle Name
https://github.com/Damla78/accenture


===
load schema to docker
docker exec -i e0 mysql -uroot -proot < src/main/resources/schema.sql

== start using host network
docker run --net=host   test-accenture-spring

**START MY SQL CONTAINER**

    docker run --detach --name accenture-mysql-2 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=accenturedb -e MYSQL_USER=sa -e MYSQL_PASSWORD=sa -d mysql

application properties
finalName
build
-docker pull adoptopenjdk/openjdk8
docker pull openjdk:8

**BUILD DOCKER IMAGE**
In Intellij terminal, run following command

    docker build -t flightdb .

**INTERACT WITH MY DATABASE**

    docker run -it --link flight-mysql --rm mysql sh -c 'exec mysql 
    -h"$MYSQL_POORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_POORT" 
    -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
docker run -it --link flight:mysql --rm mysql sh -c 
'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot 
-p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'


FROM adoptopenjdk/openjdk8:ubi
ADD target/accenture-spring.jar accenture-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=mysql", "-jar","/accenture-spring.jar"]

FROM openjdk:8
ADD target/accenture-spring.jar accenture-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=mysql", "-jar","accenture-spring.jar"]
