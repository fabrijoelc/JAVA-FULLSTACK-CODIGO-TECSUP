package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AsignaturaEntity;
import com.codigo.spring.repository.AsignaturaRepository;
import com.codigo.spring.request.AsignaturaRequest;
import com.codigo.spring.response.AsignaturaResponse;
import com.codigo.spring.service.AsignaturaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaServiceImpl(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public AsignaturaResponse save(AsignaturaRequest request) {
        AsignaturaEntity asignatura = new AsignaturaEntity();
        asignatura.setNombre(request.getNombre());
        asignatura.setDescripcion(request.getDescripcion());

        AsignaturaEntity saved = asignaturaRepository.save(asignatura);
        return toResponse(saved);
    }

    @Override
    public Optional<AsignaturaResponse> getById(Integer id) {
        return asignaturaRepository.findById(id).map(this::toResponse);
    }

    private AsignaturaResponse toResponse(AsignaturaEntity asignatura) {
        return new AsignaturaResponse(
                asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getDescripcion()
        );
    }
}
