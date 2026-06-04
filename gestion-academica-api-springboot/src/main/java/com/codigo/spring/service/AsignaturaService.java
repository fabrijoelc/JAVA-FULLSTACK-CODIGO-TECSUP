package com.codigo.spring.service;

import com.codigo.spring.request.AsignaturaRequest;
import com.codigo.spring.response.AsignaturaResponse;

import java.util.Optional;

public interface AsignaturaService {
    AsignaturaResponse save(AsignaturaRequest request);

    Optional<AsignaturaResponse> getById(Integer id);
}
