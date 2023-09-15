# Use uma imagem Java como base
FROM openjdk:17

# Copie o arquivo JAR do seu aplicativo para o contêiner
COPY target/meusgastos-0.0.1-SNAPSHOT.jar /app.jar

# Exponha a porta em que seu aplicativo está ouvindo (caso necessário)
EXPOSE 8080

# Comando de inicialização do aplicativo
CMD ["java", "-jar", "/app.jar"]