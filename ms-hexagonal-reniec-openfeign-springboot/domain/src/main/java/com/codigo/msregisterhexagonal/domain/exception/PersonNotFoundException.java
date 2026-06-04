package com.codigo.msregisterhexagonal.domain.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String dni) {
        super("No se encontro una persona registrada con DNI: " + dni);
    }
}
