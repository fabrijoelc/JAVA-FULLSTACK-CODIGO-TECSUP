package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.repository.AerolineaRepository;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AerolineaService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    private final AerolineaRepository aerolineaRepository;


    public AerolineaServiceImpl(AerolineaRepository aerolineaRepository) {
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public AerolineaEntity save(AerolineaEntity aerolinea) {
        return aerolineaRepository.save(aerolinea);
    }

    @Override
    public List<AerolineaEntity> findAll() {
        return aerolineaRepository.findAll();
    }

    @Override
    public ResponseBase<AerolineaEntity> findById(int id) {
        Optional<AerolineaEntity> aerolineaOptional = aerolineaRepository.findById(id);

        if (aerolineaOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND, Optional.of(aerolineaOptional.get()));
    }

    @Override
    public ResponseBase<AerolineaEntity> updateById(AerolineaEntity aerolinea) {
        if(!aerolineaRepository.existsById(aerolinea.getId())){
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        AerolineaEntity aerolineaActualizada = aerolineaRepository.save(aerolinea);
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_UPDATED, Optional.of(aerolineaActualizada));
    }
}
