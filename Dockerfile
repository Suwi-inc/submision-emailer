# Build Stage
FROM maven:3.9.8-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install -U

FROM openjdk:21

COPY --from=build /app/target/email-0.0.1-SNAPSHOT.jar /app/email.jar

COPY src/main/resources/application.yml /app/application.yml

WORKDIR /app

EXPOSE 8080

CMD ["java", "-Dspring.config.location=file:/app/application.yml", "-jar", "email.jar"]
