package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.mapper.AvionMapper;
import com.codigo.spring.repository.AerolineaRepository;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AvionService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvionServiceImpl implements AvionService {

    private final AvionRepository avionRepository;
    private final AerolineaRepository aerolineaRepository;

    public AvionServiceImpl(AvionRepository avionRepository, AerolineaRepository aerolineaRepository) {
        this.avionRepository = avionRepository;
        this.aerolineaRepository = aerolineaRepository;
    }

    @Override
    public AvionEntity save(AvionEntity avionEntity) {
        return avionRepository.save(avionEntity);
    }

    @Override
    public ResponseBase<AvionResponse> findById(Integer id) {
        Optional<AvionEntity> avionEntityOptional = avionRepository.findById(id);

        if (avionEntityOptional.isEmpty()) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND,
                Optional.of(toAvionResponse(avionEntityOptional.get())));
    }

    @Override
    public List<AvionResponse> findByModelo(String modelo) {
        List<AvionEntity> list = avionRepository.findByModelo(modelo);
        List<AvionResponse> responseList = new ArrayList<>();

        for (AvionEntity avionEntity : list) {
            responseList.add(toAvionResponse(avionEntity));
        }
        return responseList;
    }

    @Override
    public List<AvionResponse> findAll() {
        List<AvionEntity> list = avionRepository.findAll();
        List<AvionResponse> responseList = new ArrayList<>();

        for (AvionEntity avionEntity : list) {
            responseList.add(toAvionResponse(avionEntity));
        }
        return responseList;
    }

    @Override
    public List<AvionResponse> findAllCapacidad(int min, int max) {
        List<AvionEntity> list = avionRepository.findByCapacidadBetween(min, max);
        List<AvionResponse> responseList = new ArrayList<>();

        for(AvionEntity avionEntity : list){
            responseList.add(toAvionResponse(avionEntity));
        }
        return responseList;
    }

    @Override
    public ResponseBase<AvionResponse> updateAerolinea(int idAvion, int idNuevaAerolinea) {
        Optional<AvionEntity> avionEntityOptional = avionRepository.findById(idAvion);
        Optional<AerolineaEntity> aerolineaEntityOptional = aerolineaRepository.findById(idNuevaAerolinea);

        if (avionEntityOptional.isPresent() && aerolineaEntityOptional.isPresent()){
            AvionEntity avionEntity = avionEntityOptional.get();
            AerolineaEntity aerolineaEntity = aerolineaEntityOptional.get();

            avionEntity.setAerolinea(aerolineaEntity);
            avionRepository.save(avionEntity);

            return new ResponseBase<>(200, "Actualizacion correcta", Optional.of(toAvionResponse(avionEntity)));
        }
        return new ResponseBase<>(404, "No se encontró el avión o la aerolínea", Optional.empty());
    }

    private AvionResponse toAvionResponse(AvionEntity avionEntity){
        return AvionMapper.INSTANCE.toAvionResponse(avionEntity, avionEntity.getAerolinea());
    }
}
