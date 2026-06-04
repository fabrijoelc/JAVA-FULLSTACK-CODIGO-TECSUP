# webclient-reniec-api-springboot

API Spring Boot que consume Decolecta/RENIEC usando WebClient y programacion reactiva con `Mono`.

## Que aprende este proyecto

Este proyecto muestra como consumir una API externa desde Spring Boot usando `WebClient`, el cliente HTTP moderno de Spring WebFlux.

Flujo:

```text
Postman
  -> Spring Boot Controller
  -> ReniecService
  -> WebClient
  -> Decolecta/RENIEC
```

`WebClient` devuelve un `Mono<ReniecResponse>`, que representa una respuesta futura de 0 o 1 elemento. Por eso el codigo trabaja con estilo reactivo: la llamada HTTP no se maneja como una respuesta bloqueante tradicional.

## Tecnologias

- Java 17
- Spring Boot 3.3.5
- Spring WebFlux
- WebClient
- Project Reactor
- Lombok
- Maven

## Configuracion

No subas tu token real a GitHub. Configuralo como variable de entorno:

```powershell
$env:DECOLECTA_TOKEN="tu_token_de_decolecta"
```

Tambien puedes revisar el archivo de ejemplo:

```text
src/main/resources/application-example.properties
```

## Ejecutar

```powershell
.\mvnw.cmd spring-boot:run
```

## Endpoint

Consultar persona por DNI:

```http
GET http://localhost:8080/api/reniec/71324431
```

Respuesta esperada:

```json
{
  "nombres": "FABRIZIO JOEL",
  "apellidoPaterno": "ALLCCA",
  "apellidoMaterno": "PELAEZ",
  "nombreCompleto": "FABRIZIO JOEL ALLCCA PELAEZ",
  "numeroDocumento": "71324431"
}
```

## Nota

Este proyecto es un ejemplo pequeno de clase para recordar el uso de WebClient. No usa base de datos porque su objetivo es enfocarse en consumo de APIs externas con flujo reactivo.
