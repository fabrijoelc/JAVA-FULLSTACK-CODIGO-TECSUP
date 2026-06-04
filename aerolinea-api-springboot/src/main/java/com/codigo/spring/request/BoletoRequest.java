package com.codigo.spring.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoletoRequest {
    @NotNull(message = "El idVuelo es obligatorio")
    private Long idVuelo;
    @Min(value = 1, message = "El idPasajero debe ser mayor a 0")
    private int idPasajero;
    @Min(value = 1, message = "El asiento debe ser mayor a 0")
    private int asiento;
}
