#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=/Users/rodrigosanchis/proyectos/up/tp.integrador/target/tp.integrador-0.0.1-SNAPSHOT.jar
#RUN ls > /tmp/foo.txt
#COPY ${JAR_FILE} app_abm_users.jar
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "app_abm_users.jar"]


FROM openjdk:17-jdk-slim as builder
WORKDIR /tp.integrador
COPY . .
RUN ./mvnw clean install -DskipTests

# Definir variables de construcción para que se pasen desde Docker Compose
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG MYSQL_ROOT_PASSWORD
ARG MYSQL_PASSWORD
ARG MYSQL_USER
ARG MYSQL_DATABASE

# Establecer las variables de entorno para el contenedor en tiempo de ejecución
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_DATABASE=${MYSQL_DATABASE}

# Usa una imagen más ligera de JDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /tp.integrador
COPY --from=builder /tp.integrador/target/*.jar app_abm_users.jar

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java", "-jar", "app_abm_users.jar"]