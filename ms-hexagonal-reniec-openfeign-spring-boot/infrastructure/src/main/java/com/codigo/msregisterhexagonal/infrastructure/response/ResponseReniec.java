package com.codigo.msregisterhexagonal.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseReniec {
    @JsonProperty("first_name")
    private String nombres;

    @JsonProperty("first_last_name")
    private String apellidoPaterno;

    @JsonProperty("second_last_name")
    private String apellidoMaterno;

    @JsonProperty("full_name")
    private String nombreCompleto;

    private String tipoDocumento;

    @JsonProperty("document_number")
    private String numeroDocumento;

    private String digitoVerificador;
}
