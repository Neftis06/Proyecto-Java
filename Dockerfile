# 1. Imagen base ultraligera de Java 17
FROM eclipse-temurin:21-jre-alpine

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiar el .jar generado por Maven al contenedor
COPY target/*.jar app.jar

# 4. Exponer el puerto donde corre Spring Boot
EXPOSE 8080

# 5. Comando de arranque de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]