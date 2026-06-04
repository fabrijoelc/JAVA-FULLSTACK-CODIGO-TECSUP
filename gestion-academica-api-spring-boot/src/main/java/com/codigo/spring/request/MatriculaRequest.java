package com.codigo.spring.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaRequest {
    @NotBlank(message = "El DNI del estudiante es obligatorio")
    private String estudiante;

    @NotNull(message = "El curso es obligatorio")
    private Integer curso;
}
