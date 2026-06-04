package com.codigo.apis_externas.aggregates.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReniecResponse {
    @JsonAlias("first_name")
    private String nombres;

    @JsonAlias("first_last_name")
    private String apellidoPaterno;

    @JsonAlias("second_last_name")
    private String apellidoMaterno;

    @JsonAlias("full_name")
    private String nombreCompleto;

    @JsonAlias("document_number")
    private String numeroDocumento;
}
