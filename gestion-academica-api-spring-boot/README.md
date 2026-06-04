# Gestión Académica API

API REST desarrollada con Spring Boot para gestionar profesores, asignaturas, cursos, estudiantes y matrículas.

## Tecnologías

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Jakarta Validation
- Swagger / OpenAPI
- Maven

## Funcionalidades

- Crear y consultar profesores.
- Crear y consultar asignaturas.
- Crear, consultar y actualizar cursos.
- Crear y consultar estudiantes.
- Crear y consultar matrículas.
- Respuestas estandarizadas con `ResponseBase`.
- Validación de datos de entrada con `@Valid`.
- Manejo global de errores con `GlobalExceptionHandler`.
- Documentación interactiva con Swagger.

## Estructura del proyecto

```text
src/main/java/com/codigo/spring
├── config       # Configuración de Swagger/OpenAPI
├── controller   # Endpoints REST
├── entity       # Entidades JPA
├── exception    # Manejo global de errores
├── repository   # Acceso a base de datos
├── request      # DTOs de entrada
├── response     # DTOs de salida
└── service      # Lógica de negocio
```

## Base de datos

La API usa PostgreSQL con una base de datos llamada:

```text
gestion_academica
```

El script para crear las tablas se encuentra en:

```text
database/schema.sql
```

## Configuración

La conexión principal está en:

```text
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_academica
spring.datasource.username=postgres
spring.datasource.password=cero
```

## Ejecución

Compilar el proyecto:

```bash
./mvnw -DskipTests compile
```

En Windows:

```bash
.\mvnw.cmd -DskipTests compile
```

Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
.\mvnw.cmd spring-boot:run
```

## Swagger

Con la aplicación ejecutándose, la documentación está disponible en:

```text
http://127.0.0.1:8080/swagger-ui.html
```

También se puede acceder al documento OpenAPI en:

```text
http://127.0.0.1:8080/v3/api-docs
```

## Endpoints principales

| Recurso | Método | Endpoint |
| --- | --- | --- |
| Profesor | POST | `/api/v1/profesor/save` |
| Profesor | GET | `/api/v1/profesor/get/{dni}` |
| Asignatura | POST | `/api/v1/asignatura/save` |
| Asignatura | GET | `/api/v1/asignatura/get/{id}` |
| Curso | POST | `/api/v1/curso/save` |
| Curso | GET | `/api/v1/curso/get/{id}` |
| Curso | PUT | `/api/v1/curso/update/{id}` |
| Estudiante | POST | `/api/v1/estudiante/save` |
| Estudiante | GET | `/api/v1/estudiante/get/{dni}` |
| Matrícula | POST | `/api/v1/matricula/save` |
| Matrícula | GET | `/api/v1/matricula/get/{id}` |

## Documentación adicional

- [Diagrama de clases](docs/diagrama-clases.md)
- [Validaciones y excepciones](docs/validaciones-y-excepciones.md)
- [Script de base de datos](database/schema.sql)
