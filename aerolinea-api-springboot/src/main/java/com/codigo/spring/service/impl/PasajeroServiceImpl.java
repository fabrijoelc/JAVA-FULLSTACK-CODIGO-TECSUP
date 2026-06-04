package com.codigo.spring.service.impl;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.mapper.PasajeroMapper;
import com.codigo.spring.repository.BoletoRepository;
import com.codigo.spring.repository.PasajeroRepository;
import com.codigo.spring.response.PasajeroInfoResponse;
import com.codigo.spring.response.PasajeroResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.PasajeroService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroServiceImpl implements PasajeroService {

    private final PasajeroRepository pasajeroRepository;
    private final BoletoRepository boletoRepository;

    public PasajeroServiceImpl(PasajeroRepository pasajeroRepository, BoletoRepository boletoRepository) {
        this.pasajeroRepository = pasajeroRepository;
        this.boletoRepository = boletoRepository;
    }

    @Override
    public ResponseBase<PasajeroResponse> save(PasajeroEntity pasajeroEntity) {
        PasajeroEntity pasajeroGuardado = pasajeroRepository.save(pasajeroEntity);
        PasajeroResponse response = PasajeroMapper.INSTANCE.toPasajeroResponse(pasajeroGuardado);

        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCES_INSERT, Optional.of(response));
    }

    @Override
    public ResponseBase<PasajeroResponse> findById(Integer id) {
        Optional<PasajeroEntity> pasajeroOptional = pasajeroRepository.findById(id);

        if (pasajeroOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        PasajeroResponse response = PasajeroMapper.INSTANCE.toPasajeroResponse(pasajeroOptional.get());
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND, Optional.of(response));
    }

    @Override
    public ResponseBase<PasajeroInfoResponse> findBoleto(Integer id) {
        Optional<PasajeroEntity> pasajeroOptional = pasajeroRepository.findById(id);

        if (pasajeroOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        List<BoletosEntity> boletos = boletoRepository.findBoletosbyPasajeroId(id);
        PasajeroInfoResponse response = PasajeroMapper.INSTANCE.toPasajeroInfoResponse(pasajeroOptional.get(), boletos);

        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND, Optional.of(response));
    }
}
