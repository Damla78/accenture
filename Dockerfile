FROM openjdk:8
ADD target/accenture-spring.jar accenture-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","accenture-spring.jar"]
