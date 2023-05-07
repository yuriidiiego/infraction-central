FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app

COPY target/infra-traker-0.0.1-SNAPSHOT.jar infra-tracker.jar

EXPOSE 8080

CMD ["java", "-jar", "infra-tracker.jar"]

