package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BoletoResponse {
    private String nombre;
    private String apellido;
    private int asiento;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
}
