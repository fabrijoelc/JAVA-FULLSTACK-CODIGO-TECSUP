package com.codigo.spring.service.impl;

import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.mapper.PilotoMapper;
import com.codigo.spring.repository.PilotoRepository;
import com.codigo.spring.response.PilotoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.PilotoService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PilotoServiceImpl implements PilotoService {

    private final PilotoRepository pilotoRepository;

    public PilotoServiceImpl(PilotoRepository pilotoRepository) {
        this.pilotoRepository = pilotoRepository;
    }

    @Override
    public ResponseBase<PilotoResponse> save(PilotoEntity pilotoEntity) {
        PilotoEntity pilotoGuardado = pilotoRepository.save(pilotoEntity);
        PilotoResponse response = PilotoMapper.INSTANCE.toPilotoResponse(pilotoGuardado);

        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCES_INSERT, Optional.of(response));
    }

    @Override
    public ResponseBase<PilotoResponse> findById(Integer id) {
        Optional<PilotoEntity> pilotoOptional = pilotoRepository.findById(id);

        if (pilotoOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        PilotoResponse response = PilotoMapper.INSTANCE.toPilotoResponse(pilotoOptional.get());
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND, Optional.of(response));
    }

    @Override
    public List<PilotoResponse> findAll() {
        List<PilotoEntity> pilotos = pilotoRepository.findAll();
        List<PilotoResponse> responses = new ArrayList<>();

        for (PilotoEntity piloto : pilotos) {
            responses.add(PilotoMapper.INSTANCE.toPilotoResponse(piloto));
        }
        return responses;
    }
}
