FROM openjdk:17-jdk-alpine
COPY build/libs/project-api-0.0.1.jar project-api.jar
ENTRYPOINT ["java", "-jar", "/project-api.jar"]