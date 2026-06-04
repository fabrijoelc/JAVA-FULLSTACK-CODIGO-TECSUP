package com.codigo.msregisterhexagonal.infrastructure.exception;

public class ConsultaSunatException extends RuntimeException {

    public ConsultaSunatException(String message) {
        super(message);
    }

    public ConsultaSunatException(String message, Throwable cause) {
        super(message, cause);
    }
}
