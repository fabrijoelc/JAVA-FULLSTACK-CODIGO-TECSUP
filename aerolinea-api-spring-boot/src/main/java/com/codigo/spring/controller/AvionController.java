package com.codigo.spring.controller;

import com.codigo.spring.entity.AvionEntity;
import com.codigo.spring.response.AvionResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AvionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avion")
@Tag(name = "Aviones", description = "Endpoints para registrar, consultar y actualizar aviones")
public class AvionController {

    private final AvionService avionService;

    public AvionController(AvionService avionService) {
        this.avionService = avionService;
    }

    @Operation(summary = "Registrar un avion", description = "Registra un avion en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Avion registrado correctamente")
    @PostMapping("/save")
    public AvionEntity save(@Valid @RequestBody AvionEntity avionEntity) {
        return avionService.save(avionEntity);
    }

    @Operation(summary = "Buscar avion por ID", description = "Obtiene la informacion de un avion usando su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avion encontrado"),
            @ApiResponse(responseCode = "404", description = "Avion no encontrado")
    })
    @GetMapping("/find/{id}")
    public ResponseBase<AvionResponse> findById(
            @Parameter(description = "ID del avion", example = "1")
            @PathVariable Integer id) {
        return avionService.findById(id);
    }

    @Operation(summary = "Buscar aviones por modelo", description = "Lista los aviones que coinciden con el modelo indicado.")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @GetMapping("/find/modelo")
    public List<AvionResponse> findAll(
            @Parameter(description = "Modelo del avion", example = "Airbus A320")
            @RequestParam String modelo) {
        return avionService.findByModelo(modelo);
    }

    @Operation(summary = "Buscar aviones por capacidad", description = "Lista aviones cuya capacidad esta dentro del rango indicado.")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @GetMapping("/find/capacidad")
    public List<AvionResponse> findAllCapacidad(
            @Parameter(description = "Capacidad minima", example = "100")
            @RequestParam int min,
            @Parameter(description = "Capacidad maxima", example = "250")
            @RequestParam int max) {
        return avionService.findAllCapacidad(min, max);
    }

    @Operation(summary = "Actualizar aerolinea de un avion", description = "Asocia un avion existente con una nueva aerolinea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aerolinea actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Avion o aerolinea no encontrados")
    })
    @PutMapping("/update")
    public ResponseBase<AvionResponse> updateAerolinea(
            @Parameter(description = "ID del avion", example = "1")
            @RequestParam int idAvion,
            @Parameter(description = "ID de la nueva aerolinea", example = "2")
            @RequestParam int idNuevaAerolinea) {
        return avionService.updateAerolinea(idAvion, idNuevaAerolinea);
    }
}
