package com.codigo.spring.service.impl;

import com.codigo.spring.entity.BoletosEntity;
import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.mapper.BoletoMapper;
import com.codigo.spring.repository.BoletoRepository;
import com.codigo.spring.repository.PasajeroRepository;
import com.codigo.spring.repository.VueloRepository;
import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.BoletoService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletoServiceImpl implements BoletoService {

    private final BoletoRepository boletoRepository;
    private final VueloRepository vueloRepository;
    private final PasajeroRepository pasajeroRepository;

    public BoletoServiceImpl(BoletoRepository boletoRepository, VueloRepository vueloRepository, PasajeroRepository pasajeroRepository) {
        this.boletoRepository = boletoRepository;
        this.vueloRepository = vueloRepository;
        this.pasajeroRepository = pasajeroRepository;
    }

    @Override
    public ResponseBase<BoletoResponse> save(BoletoRequest boletoRequest) {
        VueloEntity vuelo = vueloRepository.findById(boletoRequest.getIdVuelo()).orElse(null);
        PasajeroEntity pasajero = pasajeroRepository.findById(boletoRequest.getIdPasajero()).orElse(null);

        if (vuelo == null || pasajero == null){
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        List<BoletosEntity> boletosByVuelo = boletoRepository.findBoletosbyVueloId(boletoRequest.getIdVuelo());

        for (BoletosEntity boleto : boletosByVuelo){
            if(boletoRequest.getAsiento() == boleto.getAsiento()){
                return new ResponseBase<>(Constants.CODE_ALREADY_EXISTS, Constants.MESSAGE_ALREADY_EXISTS, Optional.empty());
            }
        }

        BoletosEntity boleto = new BoletosEntity();
        boleto.setAsiento(boletoRequest.getAsiento());
        boleto.setVuelo(vuelo);
        boleto.setPasajero(pasajero);
        BoletosEntity boletoGuardado = boletoRepository.save(boleto);

        BoletoResponse boletoResponse = BoletoMapper.INSTANCE.toBoletoResponse(boletoGuardado, pasajero, vuelo);
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCES_INSERT, Optional.of(boletoResponse));
    }
}
