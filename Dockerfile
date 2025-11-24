FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia somente arquivos essenciais primeiro (melhor cache)
COPY pom.xml .
COPY .mvn ./.mvn
COPY mvnw .
COPY mvnw.cmd .

# Baixa dependências
RUN ./mvnw -B dependency:go-offline

# Agora copia o código fonte
COPY src ./src

# Faz o build
RUN ./mvnw -B package -DskipTests

# Executa o jar
CMD ["java", "-jar", "target/petconnect-0.0.1-SNAPSHOT.jar"]
