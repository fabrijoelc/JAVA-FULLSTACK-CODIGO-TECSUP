package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroResponse {
    private Integer id;
    private String nombre;
    private String apellido;
}
