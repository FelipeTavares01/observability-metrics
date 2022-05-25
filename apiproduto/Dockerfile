FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY ./target/produto-0.0.1-SNAPSHOT.jar /app/produto-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "produto-0.0.1-SNAPSHOT.jar"]
