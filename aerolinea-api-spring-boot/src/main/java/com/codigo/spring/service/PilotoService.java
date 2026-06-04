package com.codigo.spring.service;

import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.response.PilotoResponse;
import com.codigo.spring.response.ResponseBase;

import java.util.List;

public interface PilotoService {
    ResponseBase<PilotoResponse> save(PilotoEntity pilotoEntity);
    ResponseBase<PilotoResponse> findById(Integer id);
    List<PilotoResponse> findAll();
}
