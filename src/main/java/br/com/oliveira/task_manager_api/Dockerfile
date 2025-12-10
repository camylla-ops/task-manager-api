FROM openjdk:21-jdk-slim AS build
WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

COPY src /app/src

RUN ./mvnw clean package -DskipTests


FROM openjdk:21-jdk-slim
EXPOSE 8080
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
