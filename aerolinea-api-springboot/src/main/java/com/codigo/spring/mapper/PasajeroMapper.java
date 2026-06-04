package com.codigo.spring.mapper;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.response.PasajeroInfoResponse;
import com.codigo.spring.response.PasajeroResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PasajeroMapper {

    PasajeroMapper INSTANCE = Mappers.getMapper(PasajeroMapper.class);

    PasajeroResponse toPasajeroResponse(PasajeroEntity pasajero);

    default PasajeroInfoResponse toPasajeroInfoResponse(PasajeroEntity pasajero, List<BoletosEntity> boletos) {
        PasajeroInfoResponse response = new PasajeroInfoResponse();
        response.setNombre(pasajero.getNombre());
        response.setApellido(pasajero.getApellido());
        response.setBoletos(new ArrayList<>());

        for (BoletosEntity boleto : boletos) {
            response.getBoletos().add(new PasajeroInfoResponse.BoletoInfo(
                    boleto.getVuelo().getOrigen(),
                    boleto.getVuelo().getDestino(),
                    boleto.getAsiento()
            ));
        }
        return response;
    }
}
