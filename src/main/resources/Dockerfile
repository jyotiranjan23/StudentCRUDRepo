# Use OpenJDK 17 (or 21 if your project requires)
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files and source
COPY . .

# Build the JAR
RUN ./gradlew clean build -x test

# Run the app
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
