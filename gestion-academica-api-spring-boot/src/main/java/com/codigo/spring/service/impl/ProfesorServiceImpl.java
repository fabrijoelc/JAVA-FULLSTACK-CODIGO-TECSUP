package com.codigo.spring.service.impl;

import com.codigo.spring.entity.ProfesorEntity;
import com.codigo.spring.repository.ProfesorRepository;
import com.codigo.spring.request.ProfesorRequest;
import com.codigo.spring.response.ProfesorResponse;
import com.codigo.spring.service.ProfesorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorServiceImpl(ProfesorRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProfesorResponse save(ProfesorRequest request) {
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setDni(request.getDni());
        profesor.setNombre(request.getNombre());
        profesor.setApellido(request.getApellido());
        profesor.setEmail(request.getEmail());

        ProfesorEntity saved = repository.save(profesor);
        return toResponse(saved);
    }

    @Override
    public Optional<ProfesorResponse> getById(String dni) {
        return repository.findById(dni).map(this::toResponse);
    }

    private ProfesorResponse toResponse(ProfesorEntity profesor) {
        return new ProfesorResponse(
                profesor.getDni(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getEmail()
        );
    }
}
