# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run using Microsoft's Official Playwright+Java image
# NOTE: Change "v1.49.0" to match the version of Playwright in your pom.xml
FROM mcr.microsoft.com/playwright/java:v1.49.0-noble
WORKDIR /app

# Tell Playwright to use the pre-installed browsers instead of downloading new ones
ENV PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=true

# Copy the compiled .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Run with a restricted memory limit (-Xmx256m) for the free tier
ENTRYPOINT ["java", "-Xmx256m", "-jar", "app.jar"]