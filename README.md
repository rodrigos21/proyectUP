# Aplicación Backend de Gestión de Usuarios y Roles
### Descripción
Esta es una aplicación backend desarrollada en Java 17 utilizando Spring Boot. Proporciona una API REST que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre usuarios y roles, y un breve reporte. Además, implementa Spring Security para la autenticación de usuarios, incluyendo un endpoint de login que proporciona un token de autenticación.

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
- H2 Database
- Maven

### Instalación
1. Clona el repositorio: `git clone https://github.com/rodrigos21/proyectUP.git`
2. Navega al directorio del proyecto: `cd tu-repositorio`
3. Construye el proyecto con Maven: `mvn clean install`
4. Ejecuta la aplicación:`mvn spring-boot:run`
La aplicación estará disponible en http://localhost:8080.

### Seguridad
La aplicación utiliza JWT para la autenticación. Cada solicitud a un endpoint protegido debe incluir un token JWT en el encabezado de autorización:
- Authorization: Bearer jwt-token
#### Autenticación
POST /login: Autentica un usuario y proporciona un token JWT.
- Request Body: (Credenciales de prueba)  `{"username": "admin@admin.com","password": "1234"}`
- Response Body:`{"token": "jwt-token"}`

## Testing
- Unit Tests
- Postman collection