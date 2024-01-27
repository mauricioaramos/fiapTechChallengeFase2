FROM amazoncorretto:17.0.7-alpine

# Set environment variables
ENV DEBIAN_FRONTEND=noninteractive
ENV TZ=UTC

# Update and install OpenJDK
#RUN apk update && apk  add 17-jdk-alpine

# Set the working directory in the container
WORKDIR /app
COPY . .
# Install Maven
RUN apk --no-cache add maven


RUN mvn clean install -DskipTests

# Copy the application JAR file into the container at /app
COPY  target/estacionamento-0.0.1-SNAPSHOT.jar /app/estacionamento-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application when the container starts

CMD ["java", "-jar", "estacionamento-0.0.1-SNAPSHOT.jar"]
