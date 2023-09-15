# Use a imagem oficial do Maven
FROM maven:3.8.4-openjdk-17-slim

# Crie um diretório de trabalho
WORKDIR /app

# Copie o código-fonte e o arquivo pom.xml para o diretório de trabalho
COPY . .

# Compile o projeto com o Maven
RUN mvn clean package

# Exponha a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
CMD ["java", "-jar", "target/meusgastos-0.0.1-SNAPSHOT.jar"]