# ms-productos

Microservicio de gestion de productos con Java 17, Spring Boot 3.3.5, Eureka, Config Server, Vault, validacion contra `ms-auth`, JUnit, Mockito y Jacoco.

## Endpoints

- `POST /productos`: crea producto. Requiere rol `ADMIN` o `SUPERADMIN`.
- `GET /productos`: lista productos. Requiere rol `ADMIN` o `SUPERADMIN`.
- `GET /productos/{id}`: obtiene producto. Requiere rol `ADMIN` o `SUPERADMIN`.
- `PUT /productos/{id}`: actualiza producto. Requiere rol `ADMIN` o `SUPERADMIN`.
- `DELETE /productos/{id}`: elimina producto. Requiere rol `ADMIN` o `SUPERADMIN`.
- `GET /productos/{id}/exists`: valida existencia para comunicacion interna con `ms-ordenes`. Requiere token JWT valido.

## Configuracion

- `ms.auth.validate`: URL de `ms-auth` para validar JWT. Por defecto `http://localhost:8080/auth/validate`.
- Configuracion de base de datos y secretos se externaliza con Config Server y Vault.

## Pruebas

```bash
./mvnw test
```

Los tests usan H2 y desactivan Config Server, Vault y Eureka desde `src/test/resources`.
