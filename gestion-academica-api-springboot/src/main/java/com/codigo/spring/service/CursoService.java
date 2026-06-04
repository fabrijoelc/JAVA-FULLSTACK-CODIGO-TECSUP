package com.codigo.spring.service;

import com.codigo.spring.request.CursoRequest;
import com.codigo.spring.request.CursoUpdateRequest;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.CursoResponse;

public interface CursoService {
    ResponseBase<CursoResponse> save(CursoRequest request);

    ResponseBase<CursoResponse> getById(Integer id);

    ResponseBase<CursoResponse> update(Integer id, CursoUpdateRequest request);
}
