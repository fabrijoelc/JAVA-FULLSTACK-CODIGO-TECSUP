package com.codigo.spring.service;

import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.VueloResponse;

import java.time.LocalDate;
import java.util.List;

public interface VueloService {
    ResponseBase<VueloResponse> save(VueloRequest vueloRequest);
    ResponseBase<VueloResponse> findById(Long id);
    List<VueloResponse> findByFechaSalida(LocalDate fechaSalida);
    ResponseBase<VueloResponse> addPilotosToVuelo (VueloRequestUpdatePilotos vueloRequestUpdatePilotos);

}
