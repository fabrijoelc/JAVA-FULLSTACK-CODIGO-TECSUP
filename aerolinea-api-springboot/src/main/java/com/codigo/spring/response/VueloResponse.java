package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VueloResponse {
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private String origen;
    private String destino;

    private AvionResponseBase avion;
    private List<String> pilotosVuelo;
}
