package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CursoResponse {
    private String nombre;
    private String descripcion;
    private String profesor;
    private String asignatura;
}
