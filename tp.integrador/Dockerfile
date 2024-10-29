FROM openjdk:17-jdk-slim as builder
WORKDIR /tp.integrador
COPY ./tp.integrador .
#RUN ls
RUN ./mvnw clean install -DskipTests

# Env variables build
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG POSTGRES_DB
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD

# Env variables runtime
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV POSTGRES_DB=${POSTGRES_DB}
ENV POSTGRES_USER=${POSTGRES_USER}
ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}

FROM openjdk:17-jdk-slim
WORKDIR /tp.integrador
COPY --from=builder /tp.integrador/target/*.jar app_abm_users.jar

ENTRYPOINT ["java", "-jar", "app_abm_users.jar"]