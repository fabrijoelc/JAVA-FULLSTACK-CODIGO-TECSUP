package com.codigo.spring.mapper;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.response.BoletoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoletoMapper {

    BoletoMapper INSTANCE = Mappers.getMapper(BoletoMapper.class);

    @Mappings(value = {
            @Mapping(target = "nombre", source = "pasajero.nombre"),
            @Mapping(target = "apellido", source = "pasajero.apellido"),
            @Mapping(target = "asiento", source = "boleto.asiento"),
            @Mapping(target = "origen", source = "vuelo.origen"),
            @Mapping(target = "destino", source = "vuelo.destino"),
            @Mapping(target = "fechaSalida", source = "vuelo.fechaSalida"),
            @Mapping(target = "fechaLlegada", source = "vuelo.fechaLlegada")
    })
    BoletoResponse toBoletoResponse(BoletosEntity boleto, PasajeroEntity pasajero, VueloEntity vuelo);
}
