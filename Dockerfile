# Etapa 1: build da aplicação
From openjdk:21-jdk-slim AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o build da aplicação(o jar)
COPY target/maquinario-0.0.1-SNAPSHOT.jar app.jar

# Imagem final para rodar o app
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia o .jar gerado anteriormente.
COPY --from=builder /app/app.jar app.jar
	
# Expondo porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]