package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorResponse {
    private String dni;
    private String nombre;
    private String apellido;
    private String email;
}
