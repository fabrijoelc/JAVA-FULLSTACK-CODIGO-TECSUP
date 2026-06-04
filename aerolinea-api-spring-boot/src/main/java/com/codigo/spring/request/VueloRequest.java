package com.codigo.spring.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VueloRequest {
    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDate fechaSalida;
    @NotNull(message = "La fecha de llegada es obligatoria")
    private LocalDate fechaLlegada;
    @NotBlank(message = "El origen es obligatorio")
    private String origen;
    @NotBlank(message = "El destino es obligatorio")
    private String destino;
    @Min(value = 1, message = "El avionId debe ser mayor a 0")
    private int avionId;
}
