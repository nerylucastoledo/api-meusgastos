# Use uma imagem Java como base
FROM openjdk:11

# Copie o arquivo JAR do seu aplicativo para o contêiner
COPY target/seu-app.jar /app.jar

# Exponha a porta em que seu aplicativo está ouvindo (caso necessário)
EXPOSE 8080

# Comando de inicialização do aplicativo
CMD ["java", "-jar", "/app.jar"]