package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.entity.VueloEntity;
import com.codigo.spring.mapper.VueloMapper;
import com.codigo.spring.repository.AvionRepository;
import com.codigo.spring.repository.PilotoRepository;
import com.codigo.spring.repository.VueloRepository;
import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import com.codigo.spring.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final AvionRepository avionRepository;
    private final PilotoRepository pilotoRepository;

    public VueloServiceImpl(VueloRepository vueloRepository, AvionRepository avionRepository, PilotoRepository pilotoRepository){
        this.vueloRepository = vueloRepository;
        this.avionRepository = avionRepository;
        this.pilotoRepository = pilotoRepository;
    }

    @Override
    public ResponseBase<VueloResponse> save(VueloRequest vueloRequest) {
        if (vueloRequest.getFechaLlegada().isBefore(vueloRequest.getFechaSalida())) {
            return new ResponseBase<>(Constants.CODE_ERROR, Constants.MESSAGE_INVALID_DATES, Optional.empty());
        }

        Optional<AvionEntity> optionalAvionEntity = avionRepository.findById(vueloRequest.getAvionId());

        if(optionalAvionEntity.isEmpty()){
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        VueloEntity vueloEntity = VueloMapper.INSTANCE.toVueloEntity(vueloRequest, optionalAvionEntity.get());
        VueloEntity vueloGuardado = vueloRepository.save(vueloEntity);

        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCES_INSERT,
                Optional.of(toVueloResponse(vueloGuardado)));
    }

    @Override
    public ResponseBase<VueloResponse> findById(Long id) {
        Optional<VueloEntity> optionalVuelo = vueloRepository.findById(id);

        if (optionalVuelo.isEmpty()){
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_FIND,
                Optional.of(toVueloResponse(optionalVuelo.get())));
    }

    @Override
    public List<VueloResponse> findByFechaSalida(LocalDate fechaSalida) {
        List<VueloEntity> vueloEntities = vueloRepository.findByFechaSalida(fechaSalida);
        List<VueloResponse> vueloResponses = new ArrayList<>();

        for (VueloEntity vueloEntity : vueloEntities) {
            vueloResponses.add(toVueloResponse(vueloEntity));
        }
        return vueloResponses;
    }

    @Override
    public ResponseBase<VueloResponse> addPilotosToVuelo(VueloRequestUpdatePilotos vueloRequestUpdatePilotos) {

        List<PilotoEntity> nuevosPilotos = pilotoRepository.findByIdIn(vueloRequestUpdatePilotos.getIdsPilotos());
        Optional<VueloEntity> vueloEntityOptional = vueloRepository.findById(vueloRequestUpdatePilotos.getIdVuelo());

        if (vueloEntityOptional.isEmpty() || nuevosPilotos.size() != vueloRequestUpdatePilotos.getIdsPilotos().size()) {
            return new ResponseBase<VueloResponse>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }

        VueloEntity vueloEntity = vueloEntityOptional.get();
        List<PilotoEntity> pilotosVuelo = vueloEntity.getPilotos();

        if (pilotosVuelo == null) {
            pilotosVuelo = new ArrayList<>();
            vueloEntity.setPilotos(pilotosVuelo);
        }

        for (PilotoEntity piloto : nuevosPilotos) {
            if (!pilotosVuelo.contains(piloto)) {
                pilotosVuelo.add(piloto);
                piloto.getVuelos().add(vueloEntity);
            }
        }

        vueloRepository.save(vueloEntity);

        VueloResponse vueloResponse = toVueloResponse(vueloEntity);
        vueloResponse.setPilotosVuelo(processPilotos(pilotosVuelo));

        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_UPDATED, Optional.of(vueloResponse));
    }

    private List<String> processPilotos(List<PilotoEntity> pilotoEntities){
        List<String> pilotos = new ArrayList<>();
        for (PilotoEntity pilotoEntity : pilotoEntities) {
            pilotos.add(pilotoEntity.getNombre().charAt(0) + ". " + pilotoEntity.getApellido());
        }
        return pilotos;
    }

    private VueloResponse toVueloResponse(VueloEntity vueloEntity){
        return VueloMapper.INSTANCE.toVueloResponse(vueloEntity);
    }


}
