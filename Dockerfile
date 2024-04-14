# Use Amazon Corretto as the base image
FROM amazoncorretto:17.0.10-al2023-headless

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from your target folder into the container
COPY tracker-0.0.1-SNAPSHOT.jar ./app.jar

# Set the startup command to execute the jar
CMD ["java", "-jar", "/app/app.jar"]