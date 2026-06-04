# OpenFeign Decolecta RENIEC API Spring Boot

Proyecto REST con Spring Boot y Spring Cloud OpenFeign para consultar datos de DNI mediante la API de Decolecta y registrar personas en PostgreSQL.

## Tecnologias

- Java 17
- Spring Boot
- Spring Cloud OpenFeign
- Spring Data JPA
- PostgreSQL
- Docker
- Lombok

## Configuracion

Configura tu token de Decolecta como variable de entorno:

```powershell
$env:DECOLECTA_TOKEN="tu_token_de_decolecta"
```

La base de datos configurada es:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/apis_externas_db
spring.datasource.username=postgres
spring.datasource.password=cero
```

## Ejecutar

```powershell
.\mvnw.cmd spring-boot:run
```

## Endpoints

Consultar DNI:

```http
GET http://localhost:8080/api/person/find/{dni}
```

Registrar persona:

```http
POST http://localhost:8080/api/person/save/{dni}
```

Listar personas activas:

```http
GET http://localhost:8080/api/person/
```
