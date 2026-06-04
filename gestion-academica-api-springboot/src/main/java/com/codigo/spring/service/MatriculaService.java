package com.codigo.spring.service;

import com.codigo.spring.request.MatriculaRequest;
import com.codigo.spring.response.MatriculaResponse;
import com.codigo.spring.response.ResponseBase;

public interface MatriculaService {
    ResponseBase<MatriculaResponse> save(MatriculaRequest request);

    ResponseBase<MatriculaResponse> getById(Integer id);
}
