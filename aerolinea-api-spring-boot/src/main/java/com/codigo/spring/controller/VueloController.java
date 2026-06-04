package com.codigo.spring.controller;

import com.codigo.spring.request.VueloRequest;
import com.codigo.spring.request.VueloRequestUpdatePilotos;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.response.VueloResponse;
import com.codigo.spring.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
@Tag(name = "Vuelos", description = "Endpoints para registrar, consultar y asignar pilotos a vuelos")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService){
        this.vueloService = vueloService;
    }

    @Operation(summary = "Registrar un vuelo", description = "Registra un vuelo asociado a un avion existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo registrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Avion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Fechas invalidas")
    })
    @PostMapping("/save")
    public ResponseBase<VueloResponse> save(@Valid @RequestBody VueloRequest vueloRequest){
        return vueloService.save(vueloRequest);
    }

    @Operation(summary = "Buscar vuelo por ID", description = "Obtiene la informacion de un vuelo usando su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @GetMapping("/find/{id}")
    public ResponseBase<VueloResponse> findById(
            @Parameter(description = "ID del vuelo", example = "1")
            @PathVariable Long id){
        return vueloService.findById(id);
    }

    @Operation(summary = "Buscar vuelos por fecha de salida", description = "Lista vuelos desde la fecha de salida indicada.")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @GetMapping("/find")
    public List<VueloResponse> findByFecha(
            @Parameter(description = "Fecha de salida en formato yyyy-MM-dd", example = "2026-05-04")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaSalida){
        return vueloService.findByFechaSalida(fechaSalida);
    }

    @Operation(summary = "Asignar pilotos a un vuelo", description = "Asocia una lista de pilotos existentes a un vuelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pilotos asignados correctamente"),
            @ApiResponse(responseCode = "404", description = "Vuelo o pilotos no encontrados")
    })
    @PutMapping("/update/pilotos")
    public ResponseBase<VueloResponse> updatePilotos(@Valid @RequestBody VueloRequestUpdatePilotos vueloRequest){
        return vueloService.addPilotosToVuelo(vueloRequest);
    }
}
