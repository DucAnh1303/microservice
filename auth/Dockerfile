# Sử dụng JDK 17 để build ứng dụng
FROM gradle:7.6.1-jdk17-alpine AS build

WORKDIR /app

COPY . /app

RUN ./gradlew clean build

FROM openjdk:17-slim

COPY build/libs/auth-0.0.1-SNAPSHOT.jar app/auth.jar

ENTRYPOINT ["java", "-jar", "/app/auth.jar"]