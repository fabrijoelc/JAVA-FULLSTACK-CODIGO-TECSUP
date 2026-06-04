package com.codigo.spring.exception;

import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBase<List<String>>> handleValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseBase<>(
                        Constants.CODE_BAD_REQUEST,
                        Constants.MESSAGE_BAD_REQUEST,
                        Optional.of(errors)
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseBase<List<String>>> handleUnreadableRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseBase<>(
                        Constants.CODE_BAD_REQUEST,
                        Constants.MESSAGE_BAD_REQUEST,
                        Optional.of(List.of("El cuerpo de la solicitud no tiene el formato esperado."))
                ));
    }
}
