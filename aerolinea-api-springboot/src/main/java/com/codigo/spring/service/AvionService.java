package com.codigo.spring.service;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.response.ResponseBase;

import java.util.List;

public interface AvionService {
    AvionEntity save(AvionEntity avionEntity);
    ResponseBase<AvionResponse> findById(Integer id);
    List<AvionResponse> findByModelo(String modelo);
    List<AvionResponse> findAll();
    List<AvionResponse> findAllCapacidad(int min, int max);
    ResponseBase<AvionResponse> updateAerolinea(int avion, int idNuevaAerolinea);
}
