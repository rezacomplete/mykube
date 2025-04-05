# Use Maven with Java 21 for building the application
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the source code
COPY . .

# Build the application (skip tests for faster build, remove if needed)
RUN mvn clean package -DskipTests

# Use lightweight JRE with Java 21 for running the application
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
