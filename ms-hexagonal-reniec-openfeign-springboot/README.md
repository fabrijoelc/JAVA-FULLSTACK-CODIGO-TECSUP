# ms-register-hexagonal

Microservicio en Spring Boot con arquitectura hexagonal. Consulta datos de personas por DNI usando Decolecta/RENIEC mediante OpenFeign y registra la informacion en PostgreSQL y MongoDB.

## Arquitectura

El proyecto esta dividido en tres modulos:

- `application`: arranca Spring Boot, expone los controllers y configura los casos de uso.
- `domain`: contiene DTOs, puertos y casos de uso. No depende de Spring Boot para registrar sus servicios.
- `infrastructure`: contiene adaptadores, entidades, repositorios, cliente OpenFeign y configuraciones tecnicas.

Flujo principal:

```text
Postman
  -> PersonController
  -> PersonServiceIn
  -> PersonServiceImpl
  -> PersonServiceOut
  -> PersonAdapter
  -> Decolecta/RENIEC + PostgreSQL + MongoDB
```

En analogia de Dota 2: `domain` decide la jugada, `application` recibe el ping del jugador, e `infrastructure` compra los items, pone wards y habla con el mapa real.

## Tecnologias

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Data MongoDB
- PostgreSQL
- MongoDB
- OpenFeign
- Maven multi-module
- Lombok
- ModelMapper

## Base de datos

PostgreSQL guarda la version relacional en la tabla `persons`.

MongoDB guarda la version documental en la coleccion `persons`.

En este proyecto se usan ambas bases para practicar integraciones SQL y NoSQL dentro de una misma arquitectura hexagonal. Para un sistema productivo real, conviene definir una base principal o usar un patron de consistencia como outbox/reintentos si una escritura falla despues de la otra.

## Configuracion

No subas tokens reales a GitHub. Define el token de Decolecta como variable de entorno:

```powershell
$env:DECOLECTA_TOKEN="tu_token"
```

Ejemplo de configuracion disponible en:

```text
application/src/main/resources/application-example.properties
```

## Levantar dependencias

```bash
docker compose up -d
```

Esto levanta:

- PostgreSQL en `localhost:5432`
- MongoDB en `localhost:27017`

## Ejecutar el proyecto

Desde la raiz del proyecto:

```bash
./mvnw clean install -DskipTests
./mvnw -pl application spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd clean install -DskipTests
.\mvnw.cmd -pl application spring-boot:run
```

## Endpoints

Guardar persona consultando Decolecta/RENIEC:

```http
POST http://localhost:8080/v1/hexagonal/save?dni=71324431
```

Listar personas:

```http
GET http://localhost:8080/v1/hexagonal/persons
```

Buscar persona por DNI:

```http
GET http://localhost:8080/v1/hexagonal/persons/71324431
```

Eliminar persona por DNI:

```http
DELETE http://localhost:8080/v1/hexagonal/persons/71324431
```

## Notas importantes

- El controller no sabe si se usa PostgreSQL, MongoDB o Decolecta. Solo habla con el puerto de entrada.
- El caso de uso no depende de Spring como servicio. Se registra desde `UseCaseConfig`.
- Los repositorios estan en `infrastructure` porque son detalles externos.
- El token se lee desde variable de entorno para no exponer secretos en GitHub.
