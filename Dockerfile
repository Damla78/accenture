FROM openjdk:8
ADD target/accenture-spring.jar accenture-spring.jar
#EXPOSE 80
ENTRYPOINT ["java", "-jar", "accenture-spring.jar"]