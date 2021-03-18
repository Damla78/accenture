**DOCKER SHOULD INCLUDE**

    docker pull mysql
    docker pull openjdk:8

**CREATE NETWORK**
    
    docker network create accenture-network

**CREATE MYSQL CONTAINER**

    docker run --detach \
    --name accenture-mysql \
    --net=accenture-network\
    -p 3307:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=accenturedb \
    -e MYSQL_USER=sa \
    -e MYSQL_PASSWORD=sa \
    -d mysql

**LOAD SCHEMA TO DOCKER**
(a0 => ????)
-In terminal, type 'docker container ls'.
-take the container Id which you created.
-replace with 'e0'
-After replacing, run below command.

    docker exec -i e0 mysql -uroot -proot < src/main/resources/schema.sql 


**BUILD**
In intellij terminal, type below command
    
    docker build . -t accenture-spring

**RUN IMAGE**

    docker run -dit -p 8080:8080 --net=accenture-network accenture-spring
or
    docker run -dit -p 8080:8080 --net=accenture-network accenture-spring

-Go to localhost:8080/load
-Load data only one times. It will take some time to load all data.
When you see below 3 command in terminal, load process is completed successfully.
    ?*** CountrytLoad is complete successfully
    ?*** AirportLoad is complete successfully
    ?*** CountrytLoad is complete successfully
Locally it takes short time but in docker takes some time. Please wait


-Go to localhost:8080/index
-Search country with code or name. If you enter both option then code will be proceed. 
 If you enter not complete code, first found result will be shown.
-Back to localhost:8080/



**DELETE BELOW! NOTE FOR MYSELF**
FileName
docker exec -i e0 mysql -uroot -proot < src/main/resources/schema.sql
docker run --net=host   test-accenture-spring
docker run -p 8080:8080 --name accenture-spring --link accenture-mysql-2:mysql -d accenture-spring



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
