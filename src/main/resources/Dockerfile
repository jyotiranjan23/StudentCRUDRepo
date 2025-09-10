# Stage 1: Build the JAR
FROM gradle:8.4.1-jdk21 AS builder

# Set working directory inside container
WORKDIR /home/gradle/project

# Copy only Gradle files first for caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle/ gradle/

# Copy the source code
COPY src/ src/

# Build the JAR (skip tests for faster build)
RUN ./gradlew clean build -x test

# Stage 2: Create small runtime image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /home/gradle/project/build/libs/demo-0.0.1-SNAPSHOT.jar ./demo.jar

# Expose port (Railway will use dynamic $PORT)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "demo.jar"]
