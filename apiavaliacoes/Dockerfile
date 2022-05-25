FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY ./target/apiavaliacoes-0.0.1-SNAPSHOT.jar /app/apiavaliacoes-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "apiavaliacoes-0.0.1-SNAPSHOT.jar"]
