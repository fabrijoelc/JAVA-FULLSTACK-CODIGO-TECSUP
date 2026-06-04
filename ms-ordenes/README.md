# ms-ordenes

Microservicio de gestion de ordenes con Java 17, Spring Boot 3.3.5, Eureka, Config Server, Vault, validacion contra `ms-auth`, comunicacion con `ms-productos`, JUnit, Mockito y Jacoco.

## Endpoints

- `POST /ordenes`: crea orden. Requiere rol `USUARIO`.
- `GET /ordenes`: lista ordenes. Requiere rol `ADMIN` o `SUPERADMIN`.

La creacion de orden valida cada producto contra `GET /productos/{id}/exists` en `ms-productos`.

## Configuracion

- `ms.auth.validate`: URL de `ms-auth` para validar JWT. Por defecto `http://localhost:8080/auth/validate`.
- `ms.productos.url`: URL base de `ms-productos`. Por defecto `http://localhost:8081/productos`.
- Configuracion de base de datos y secretos se externaliza con Config Server y Vault.

## Pruebas

```bash
./mvnw test
```

Los tests usan H2 y desactivan Config Server, Vault y Eureka desde `src/test/resources`.
