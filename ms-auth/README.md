# ms-auth

Microservicio de autenticacion y autorizacion con Java 17, Spring Boot 3.3.5, Spring Security, JWT, Eureka, Config Server, Vault, JUnit, Mockito y Jacoco.

## Endpoints

- `POST /auth/register`: registra usuarios con rol `SUPERADMIN`, `ADMIN` o `USUARIO`.
- `POST /auth/login`: autentica credenciales y devuelve JWT.
- `GET /auth/validate`: valida `Authorization: Bearer <token>` y devuelve `id`, `email` y `roles`.
- `GET /test/superadmin`: lista usuarios `SUPERADMIN`.
- `GET /test/admin`: lista usuarios `ADMIN`.
- `GET /test/user`: lista usuarios `USUARIO`.

## Configuracion

El servicio lee configuracion externa desde Config Server y secretos desde Vault. Valores esperados:

- `url`: JDBC URL de PostgreSQL.
- `username`: usuario de base de datos.
- `password`: password de base de datos.
- `jwt.secret`: clave Base64 para firmar JWT.

## Pruebas

```bash
./mvnw test
```

Los tests usan H2 y desactivan Config Server, Vault y Eureka desde `src/test/resources`.
