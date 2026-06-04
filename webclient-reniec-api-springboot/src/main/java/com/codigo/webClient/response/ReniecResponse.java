package com.codigo.webClient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReniecResponse {
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
