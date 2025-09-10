# Stage 1: Build the JAR
FROM gradle:8.4.1-jdk21-alpine AS builder

WORKDIR /home/gradle/project

COPY build.gradle settings.gradle gradlew ./
COPY gradle/ gradle/
COPY src/ src/

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# Stage 2: Run the JAR
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /home/gradle/project/build/libs/*.jar ./demo.jar

EXPOSE 8080

CMD ["java", "-jar", "demo.jar"]