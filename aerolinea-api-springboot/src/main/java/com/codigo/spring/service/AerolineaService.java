package com.codigo.spring.service;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.response.ResponseBase;

import java.util.List;

public interface AerolineaService {

    AerolineaEntity save(AerolineaEntity aerolinea);
    List<AerolineaEntity> findAll();
    ResponseBase<AerolineaEntity> findById(int id);
    ResponseBase<AerolineaEntity> updateById(AerolineaEntity aerolinea);
}
