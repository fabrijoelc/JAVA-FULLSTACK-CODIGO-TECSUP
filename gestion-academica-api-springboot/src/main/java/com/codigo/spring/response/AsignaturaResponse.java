package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
}
