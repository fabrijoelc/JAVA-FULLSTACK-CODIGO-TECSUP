package com.codigo.spring.service;

import com.codigo.spring.request.ProfesorRequest;
import com.codigo.spring.response.ProfesorResponse;

import java.util.Optional;

public interface ProfesorService {

    ProfesorResponse save(ProfesorRequest request);

    Optional<ProfesorResponse> getById(String dni);
}
