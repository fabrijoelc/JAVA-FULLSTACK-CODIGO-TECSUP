package com.codigo.spring.service;

import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.response.PasajeroInfoResponse;
import com.codigo.spring.response.PasajeroResponse;
import com.codigo.spring.response.ResponseBase;

public interface PasajeroService {
    ResponseBase<PasajeroResponse> save(PasajeroEntity pasajeroEntity);
    ResponseBase<PasajeroResponse> findById(Integer id);
    ResponseBase<PasajeroInfoResponse> findBoleto(Integer id);
}
