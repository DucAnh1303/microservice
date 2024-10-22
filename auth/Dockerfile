FROM openjdk:17-jdk-alpine

WORKDIR /app
#copy file jar change app.jar
COPY build/libs/auth-0.0.1-SNAPSHOT.jar auth.jar

#command run application
ENTRYPOINT ["java", "-jar", "/auth.jar"]

#open port 9091 application
EXPOSE 9091


