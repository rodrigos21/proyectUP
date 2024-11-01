# Aplicación Backend de Gestión de Usuarios y Roles
### Descripción
Esta es una api está desarrollada en Java 17 utilizando Spring Boot. Proporciona una API REST que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre usuarios y roles, y un breve reporte.

### Características
- CRUD/ABM de Usuarios
- CRUD/ABM de Roles
- Reporte
- Autenticación mediante JWT Spring Security

### Tecnologías Utilizadas
- Java 17
- Spring Boot 3
- Spring Security 6
- JWT (JSON Web Token)
- PostgreSQL 
- H2 Database to unit test
- Maven
- Docker
- Sentry
- CI/CD

## Testing
- Unit Tests
- Postman collection

## Variables de entorno
| Nombre            | Descripcion                       |
|-------------------|-----------------------------------|
| DB_URL            | El host de la base de datos       |
| DB_USERNAME       | Campo usuario de la base de datos |
| DB_PASSWORD       | La contraseña de la base de datos |
| SENTRY_DSN        | DSN que te proveen                |


## Docker 
``` bash 
 docker build -f tp.integrador/Dockerfile -t test:test .
```
``` bash 
 docker run -p 8080:80 <imageId> 
```
### Docker compose
``` bash 
 docker compose up
```