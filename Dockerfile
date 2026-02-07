# Multi-stage build for optimized image size
# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/task-management-system-1.0.0.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the active profile to docker
ENV SPRING_PROFILES_ACTIVE=docker

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]