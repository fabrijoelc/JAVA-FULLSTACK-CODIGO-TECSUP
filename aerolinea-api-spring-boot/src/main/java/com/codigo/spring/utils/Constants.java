package com.codigo.spring.utils;

public class Constants {
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_ALREADY_EXISTS = 409;
    public static final int CODE_ALREADY_DELETED = 204;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public static final String MESSAGE_BAD_REQUEST = "La solicitud contiene datos invalidos.";
    public static final String MESSAGE_NOT_FOUND = "No se encontró el recurso solicitado.";
    public static final String MESSAGE_ALREADY_EXISTS = "El recurso ya existe.";
    public static final String MESSAGE_ALREADY_DELETED = "El recurso ya ha sido eliminado.";
    public static final String MESSAGE_SUCCESS_UPDATED = "Datos actualizados correctamente.";
    public static final String MESSAGE_SUCCESS_DELET = "Datos eliminado con éxito.";
    public static final String MESSAGE_SUCCES_INSERT = "Datos registrados correctamente.";
    public static final String MESSAGE_SUCCESS_FIND = "Datos encontrados correctamente.";
    public static final String MESSAGE_INVALID_DATES = "La fecha de llegada no puede ser anterior a la fecha de salida.";
}
