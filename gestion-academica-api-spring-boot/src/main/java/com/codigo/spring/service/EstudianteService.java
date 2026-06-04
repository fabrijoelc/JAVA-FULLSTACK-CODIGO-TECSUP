package com.codigo.spring.service;

import com.codigo.spring.request.EstudianteRequest;
import com.codigo.spring.response.EstudianteResponse;

import java.util.Optional;

public interface EstudianteService {
    EstudianteResponse save(EstudianteRequest request);

    Optional<EstudianteResponse> getById(String dni);
}
