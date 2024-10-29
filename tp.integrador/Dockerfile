FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/tp.integrador-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_abm_users.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_abm_users.jar"]
