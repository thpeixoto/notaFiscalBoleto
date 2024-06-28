# Use the official Maven image to build the application
FROM maven:3-eclipse-temurin-21-alpine AS target
RUN apk add --no-cache curl
WORKDIR /build

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests -Dmaven.test.skip=true

# Use the official Eclipse Temurin JDK image to run the application
FROM eclipse-temurin:21-jdk
EXPOSE 8080

# Copy the jar file from the build stage
COPY --from=target /build/target/alpe-0.0.1-SNAPSHOT.jar /app/alpe.jar

# Set the entrypoint to run the application
CMD exec java $JAVA_OPTS -jar /app/alpe.jar
