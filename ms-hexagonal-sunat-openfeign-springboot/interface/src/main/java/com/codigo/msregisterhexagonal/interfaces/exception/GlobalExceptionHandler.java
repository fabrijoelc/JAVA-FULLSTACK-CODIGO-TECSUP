package com.codigo.msregisterhexagonal.interfaces.exception;

import com.codigo.msregisterhexagonal.interfaces.dto.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ResponseBase<Object>> handleBadRequest(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseBase.error(
                        HttpStatus.BAD_REQUEST.value(),
                        "Los datos enviados son invalidos"
                ));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseBase<Object>> handleNotFound(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseBase.error(
                        HttpStatus.NOT_FOUND.value(),
                        "No se encontro la empresa solicitada"
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Object>> handleInternalServerError(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBase.error(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Ocurrio un error inesperado en el sistema"
                ));
    }
}
