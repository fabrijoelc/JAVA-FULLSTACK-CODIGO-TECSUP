package com.codigo.spring.mapper;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.response.VueloResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VueloMapper {

    VueloMapper INSTANCE = Mappers.getMapper(VueloMapper.class);

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "pilotos", ignore = true),
            @Mapping(target = "origen", source = "vueloRequest.origen"),
            @Mapping(target = "destino", source = "vueloRequest.destino"),
            @Mapping(target = "fechaSalida", source = "vueloRequest.fechaSalida"),
            @Mapping(target = "fechaLlegada", source = "vueloRequest.fechaLlegada"),
            @Mapping(target = "avion", source = "avion")
    })
    VueloEntity toVueloEntity(VueloRequest vueloRequest, AvionEntity avion);

    @Mappings(value = {
            @Mapping(target = "fechaSalida", source = "fechaSalida"),
            @Mapping(target = "fechaLlegada", source = "fechaLlegada"),
            @Mapping(target = "origen", source = "origen"),
            @Mapping(target = "destino", source = "destino"),
            @Mapping(target = "avion.capacidad", source = "avion.capacidad"),
            @Mapping(target = "avion.modelo", source = "avion.modelo"),
            @Mapping(target = "pilotosVuelo", ignore = true)
    })
    VueloResponse toVueloResponse(VueloEntity vueloEntity);
}
