package com.codigo.msregisterhexagonal.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SunatResponse {

    private String ruc;

    @JsonProperty("razon_social")
    private String razonSocial;

    @JsonProperty("numero_documento")
    private String numeroDocumento;

    @JsonProperty("nombre_comercial")
    private String nombreComercial;

    private List<String> telefonos;
    private String tipo;
    private String estado;
    private String condicion;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String ubigeo;
    private String capital;

    @JsonProperty("actividad_economica")
    private String actividadEconomica;

    @JsonProperty("numero_trabajadores")
    private Integer numeroTrabajadores;
}
