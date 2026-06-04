# Diagrama de clases

```mermaid
classDiagram
    class ProfesorEntity {
        String dni
        String nombre
        String apellido
        String email
    }

    class AsignaturaEntity {
        int id
        String nombre
        String descripcion
    }

    class CursoEntity {
        int id
        String nombre
        String descripcion
        ProfesorEntity profesor
        AsignaturaEntity asignatura
    }

    class EstudianteEntity {
        String dni
        String nombre
        String apellido
        String email
        LocalDate fechaNacimiento
    }

    class MatriculaEntity {
        int matriculaId
        EstudianteEntity estudiante
        CursoEntity curso
        LocalDate fechaInscripcion
    }

    ProfesorEntity "1" --> "0..*" CursoEntity : dicta
    AsignaturaEntity "1" --> "0..*" CursoEntity : pertenece
    EstudianteEntity "1" --> "0..*" MatriculaEntity : registra
    CursoEntity "1" --> "0..*" MatriculaEntity : contiene
```

## Relaciones principales

- Un profesor puede estar asociado a muchos cursos.
- Una asignatura puede estar asociada a muchos cursos.
- Un estudiante puede tener muchas matrículas.
- Un curso puede tener muchas matrículas.
