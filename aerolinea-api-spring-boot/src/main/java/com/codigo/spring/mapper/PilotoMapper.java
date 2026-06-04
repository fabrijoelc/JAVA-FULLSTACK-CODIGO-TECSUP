package com.codigo.spring.mapper;

import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.response.PilotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PilotoMapper {

    PilotoMapper INSTANCE = Mappers.getMapper(PilotoMapper.class);

    PilotoResponse toPilotoResponse(PilotoEntity pilotoEntity);
}
