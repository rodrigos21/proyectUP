FROM openjdk:17-jdk-slim

# Definir variables de construcción
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

# Establecer variables de entorno disponibles en tiempo de ejecución
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

ARG JAR_FILE=tp.integrador/target/tp.integrador-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_abm_users.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_abm_users.jar"]
