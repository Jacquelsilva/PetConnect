# -------- STAGE 1: Build --------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY petconnect/pom.xml .
COPY petconnect/.mvn ./.mvn
COPY petconnect/mvnw .
COPY petconnect/mvnw.cmd .

RUN chmod +x mvnw

COPY petconnect/src ./src

RUN ./mvnw -B package -DskipTests

# -------- STAGE 2: Run --------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]