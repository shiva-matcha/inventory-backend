# Use OpenJDK base image
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

# Copy source code
COPY . .

# Build app
RUN mvn clean package -DskipTests

# Use a slim JDK image for running
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy built JAR from the previous stage
COPY --from=build /app/target/inventory-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
