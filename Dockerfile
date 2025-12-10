# ESTÁGIO 1: CONSTRUÇÃO - Usa a imagem oficial Maven para compilar
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copia o pom.xml e os fontes
COPY pom.xml .
COPY src /app/src
# Compila o projeto (agora o comando 'mvn' existe!)
RUN mvn clean package -DskipTests

# ESTÁGIO 2: EXECUÇÃO - Usa apenas o JRE para ser leve
FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
WORKDIR /app
# Copia o JAR do estágio de construção
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]