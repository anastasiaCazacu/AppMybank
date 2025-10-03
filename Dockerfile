# Imagine de bază cu Java 21
FROM eclipse-temurin:21-jdk

# Setăm directorul de lucru
WORKDIR /app

# Copiem fișierul JAR generat de Maven
COPY target/mybank-0.0.1-SNAPSHOT.jar app.jar

# Expunem portul aplicației
EXPOSE 8080

# Comanda de rulare
ENTRYPOINT ["java", "-jar", "app.jar"]