# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project and skip tests to speed up deployment
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a lightweight Java Runtime (JRE)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the compiled .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port (Render/Koyeb will override this, but it's good practice)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]