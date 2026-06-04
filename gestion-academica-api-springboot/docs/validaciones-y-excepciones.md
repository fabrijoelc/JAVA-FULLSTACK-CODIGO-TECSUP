# Validaciones y excepciones

## Validaciones

Las validaciones se aplican en las clases del paquete `request` usando Jakarta Validation.

Ejemplos:

- `@NotBlank`: valida que un texto no llegue vacío.
- `@NotNull`: valida que un valor obligatorio no llegue como `null`.
- `@Email`: valida el formato de un correo.
- `@Size`: limita la cantidad máxima de caracteres.
- `@Past`: valida que una fecha sea anterior a la fecha actual.

En los controladores se usa `@Valid` junto a `@RequestBody` para activar esas validaciones antes de guardar datos.

## Manejo global de errores

La clase `GlobalExceptionHandler` centraliza los errores de la API y los devuelve con el formato `ResponseBase`.

Errores manejados:

- Validaciones incorrectas en los requests.
- JSON mal formado.
- Parámetros con formato inválido.
- Restricciones de base de datos, como correos duplicados.
- Errores internos inesperados.

Ejemplo de respuesta de validación:

```json
{
  "code": 400,
  "message": "email: El email debe tener un formato valido",
  "data": null
}
```
