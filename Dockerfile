# Use a imagem base do Ubuntu 20.04
FROM ubuntu:20.04

# Atualize o sistema e instale as dependências necessárias
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Limpe o cache do Maven
RUN rm -rf ~/.m2/repository

# Crie um diretório de trabalho
WORKDIR /app

# Copie o código-fonte e o arquivo pom.xml para o diretório de trabalho
COPY . .

# Compile o projeto com o Maven
RUN mvn clean package

# Exponha a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
CMD ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-jar", "target/meusgastos-0.0.1-SNAPSHOT.jar"]