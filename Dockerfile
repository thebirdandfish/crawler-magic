#FROM openjdk:8-jdk-alpine
FROM java:8

MAINTAINER XXX XXX@fermedu.com

ADD target/*.jar app.jar

EXPOSE 9102

ENTRYPOINT ["java", "-jar", "/app.jar"]