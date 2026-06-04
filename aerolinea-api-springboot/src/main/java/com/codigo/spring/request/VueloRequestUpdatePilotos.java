package com.codigo.spring.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VueloRequestUpdatePilotos {
    @NotNull(message = "El idVuelo es obligatorio")
    private Long idVuelo;
    @NotEmpty(message = "Debe enviar al menos un piloto")
    private List<Integer> idsPilotos;
}
