# Hexagonal SUNAT

Proyecto Spring Boot con arquitectura hexagonal para registrar empresas manualmente o consultando SUNAT mediante Decolecta con OpenFeign.

## Modulos

- `domain`: modelo de negocio, puertos y casos de uso.
- `infrastructure`: persistencia con PostgreSQL, cliente OpenFeign y configuraciones tecnicas.
- `interface`: controladores REST, respuesta estandar y manejo global de errores.
- `application`: clase principal de Spring Boot y configuracion.

## Requisitos

- Java 17+
- Maven Wrapper incluido
- PostgreSQL
- Token de Decolecta

## Base de datos

Crear la base:

```sql
CREATE DATABASE hexagonal_sunat;
```

Con Docker, si el contenedor se llama `postgres-db`:

```powershell
docker exec -e PGPASSWORD=cero postgres-db createdb -U postgres hexagonal_sunat
```

## Variables de entorno

El token no debe subirse al repositorio. Configuralo localmente:

```powershell
$env:DECOLECTA_API_TOKEN="tu_token"
```

Opcionales:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/hexagonal_sunat"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="cero"
```

## Ejecutar

```powershell
.\mvnw.cmd clean install -DskipTests
.\mvnw.cmd -f application\pom.xml spring-boot:run
```

## Endpoints

Crear empresa manual:

```http
POST http://localhost:8080/api/empresas
```

```json
{
  "razonSocial": "EMPRESA DEMO SAC",
  "numeroDocumento": "20123456789",
  "estado": "ACTIVO",
  "condicion": "HABIDO",
  "direccion": "AV. DEMO 123",
  "departamento": "LIMA",
  "provincia": "LIMA",
  "distrito": "MIRAFLORES",
  "actividadEconomica": "SERVICIOS",
  "numeroTrabajadores": 10
}
```

Crear empresa consultando SUNAT:

```http
POST http://localhost:8080/api/empresas/sunat/20601030013
```

Listar:

```http
GET http://localhost:8080/api/empresas
```

Buscar por ID:

```http
GET http://localhost:8080/api/empresas/1
```

Actualizar:

```http
PUT http://localhost:8080/api/empresas/1
```

Eliminar:

```http
DELETE http://localhost:8080/api/empresas/1
```

## Respuesta estandar

```json
{
  "codigo": 200,
  "mensaje": "mensaje descriptivo",
  "data": {}
}
```
