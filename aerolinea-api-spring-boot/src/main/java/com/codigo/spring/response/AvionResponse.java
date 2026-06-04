package com.codigo.spring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AvionResponse extends AvionResponseBase{

    private int peso;
    private String aerolinea;

    public AvionResponse(int capacidad, String modelo, int peso, String aerolinea) {
        super(capacidad, modelo);
        this.peso = peso;
        this.aerolinea = aerolinea;
    }
}
