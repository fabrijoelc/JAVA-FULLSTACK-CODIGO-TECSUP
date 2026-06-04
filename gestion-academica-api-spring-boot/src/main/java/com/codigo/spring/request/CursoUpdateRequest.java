package com.codigo.spring.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoUpdateRequest {
    @NotBlank(message = "El DNI del profesor es obligatorio")
    private String profesor;

    @NotNull(message = "La asignatura es obligatoria")
    private Integer asignatura;
}
