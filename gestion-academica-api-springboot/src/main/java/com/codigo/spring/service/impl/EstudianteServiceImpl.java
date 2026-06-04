package com.codigo.spring.service.impl;

import com.codigo.spring.entity.EstudianteEntity;
import com.codigo.spring.repository.EstudianteRepository;
import com.codigo.spring.request.EstudianteRequest;
import com.codigo.spring.response.EstudianteResponse;
import com.codigo.spring.service.EstudianteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public EstudianteResponse save(EstudianteRequest request) {
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setDni(request.getDni());
        estudiante.setNombre(request.getNombre());
        estudiante.setApellido(request.getApellido());
        estudiante.setEmail(request.getEmail());
        estudiante.setFechaNacimiento(request.getFechaNacimiento());

        EstudianteEntity saved = estudianteRepository.save(estudiante);
        return toResponse(saved);
    }

    @Override
    public Optional<EstudianteResponse> getById(String dni) {
        return estudianteRepository.findById(dni).map(this::toResponse);
    }

    private EstudianteResponse toResponse(EstudianteEntity estudiante) {
        return new EstudianteResponse(
                estudiante.getDni(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getEmail()
        );
    }
}
