FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY petconnect/pom.xml .
COPY petconnect/.mvn ./.mvn
COPY petconnect/mvnw .
COPY petconnect/mvnw.cmd .
RUN ./mvnw -B dependency:go-offline

COPY petconnect/src ./src

RUN ./mvnw -B package -DskipTests

CMD ["java", "-jar", "target/petconnect-0.0.1-SNAPSHOT.jar"]