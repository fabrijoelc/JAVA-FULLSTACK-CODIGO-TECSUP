package com.codigo.spring.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar 100 caracteres")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El DNI del profesor es obligatorio")
    private String profesor;

    @NotNull(message = "La asignatura es obligatoria")
    private Integer asignatura;
}
