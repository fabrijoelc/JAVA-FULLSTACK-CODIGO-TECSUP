package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MatriculaResponse {
    private String estudiante;
    private String curso;
    private LocalDate fecha;
}
