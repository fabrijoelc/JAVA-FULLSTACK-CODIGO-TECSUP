# RestTemplate RENIEC Spring Boot

Proyecto de ejemplo en Spring Boot para consumir una API externa de RENIEC usando `RestTemplate`.

Este proyecto no usa base de datos, JPA, Hibernate ni repositorios. Solo recibe un DNI, consulta la API externa de Decolecta y devuelve la informacion obtenida.

## Tecnologias usadas

- Java 17
- Spring Boot 3.3.5
- Spring Web
- RestTemplate
- Lombok
- Maven

## Flujo del proyecto

```text
Cliente -> Controller -> Service -> RestTemplate -> API Decolecta RENIEC -> Respuesta JSON
```

Endpoint principal:

```http
GET /api/reniec/v1/{dni}
```

Ejemplo:

```http
GET http://localhost:8080/api/reniec/v1/12345678
```

Internamente se consulta:

```text
https://api.decolecta.com/v1/reniec/dni?numero=12345678
```

## Configuracion del token

El token de Decolecta se lee desde la variable de entorno `DECOLECTA_TOKEN`.

En PowerShell:

```powershell
$env:DECOLECTA_TOKEN="tu_token_aqui"
```

## Ejecutar el proyecto

```powershell
.\mvnw.cmd spring-boot:run
```

## Ejecutar pruebas

```powershell
.\mvnw.cmd test
```

## Notas

`RestTemplate` es el cliente clasico de Spring para consumir APIs externas. En proyectos modernos de Spring, se suele recomendar `RestClient` para nuevas implementaciones sincronas, pero `RestTemplate` sigue apareciendo bastante en proyectos existentes.
