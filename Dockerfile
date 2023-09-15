FROM ubuntu:latest as BUILD
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY target/meusgastos-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]