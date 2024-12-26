# Étape 1 : Utiliser Maven avec Java 21 pour la compilation
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Vérifier les versions de Java et Maven
RUN java -version && mvn -version

# Nettoyer les dépendances et compiler
RUN mvn dependency:purge-local-repository -DreResolve=true && mvn clean package -DskipTests

# Étape 2 : Utiliser une image légère pour l'exécution
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copier l'artefact généré
COPY --from=builder /app/target/gestion-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
