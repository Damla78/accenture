**Create a database**
**Type:**

$ docker pull mysql
$ docker run --name accenture-mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=flightdb -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:latest
$ docker build . -t accenture-spring


docker network create accenture-mysql
docker container run --name mysqlcontainer --network accenture-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=flightdb -d mysql:latest
docker container logs -f ae
docker container exec -it ae bash
mysql -uroot -proot

spring.datasource.url=jdbc:mysql://mysqlcontainer/flightdb
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.platform=mysql
spring.datasource.initialization-mode=always

From openjdk:8
copy ./target/employee-jdbc-0.0.1-SNAPSHOT.jar employee-jdbc-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","employee-jdbc-0.0.1-SNAPSHOT.jar"]

docker image build -t flight-jdbc .
docker container run --network accenture-mysql --name accenture-jdbc-container -p 8080:8080 -d flight-jdbc







Start Mysql Container
   
    docker run --name mysql57 -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql/mysql-server:5.7_

This command access the mysql container and allow to execute commands on database. Inside a container, type:
 
    docker exec -it mysql57 bash

    mysql -h localhost -u root -p
Remember, the password is 1234. 
First, we need to create a user for out-of-container access because root access is not allowed:

    CREATE USER 'demo_flight' IDENTIFIED BY 'damla';
    grant all on *.* to 'demo_flight'@'%' identified by '1234';
    FLUSH PRIVILEGES;

Start & Stop Docker
    
    docker start mysql57
    docker stop mysql57