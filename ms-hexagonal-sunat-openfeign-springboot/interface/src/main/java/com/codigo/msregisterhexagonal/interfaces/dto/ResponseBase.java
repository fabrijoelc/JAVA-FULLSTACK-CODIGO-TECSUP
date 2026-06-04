package com.codigo.msregisterhexagonal.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase<T> {

    private Integer codigo;
    private String mensaje;
    private T data;

    public static <T> ResponseBase<T> success(Integer codigo, String mensaje, T data) {
        return new ResponseBase<>(codigo, mensaje, data);
    }

    public static ResponseBase<Object> error(Integer codigo, String mensaje) {
        return new ResponseBase<>(codigo, mensaje, null);
    }
}
