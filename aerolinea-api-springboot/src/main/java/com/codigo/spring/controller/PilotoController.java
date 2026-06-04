package com.codigo.spring.controller;

import com.codigo.spring.entity.PilotoEntity;
import com.codigo.spring.response.PilotoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.PilotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/piloto")
@Tag(name = "Pilotos", description = "Endpoints para registrar y consultar pilotos")
public class PilotoController {

    private final PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    @Operation(summary = "Registrar un piloto", description = "Registra un piloto en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Piloto registrado correctamente")
    @PostMapping("/save")
    public ResponseBase<PilotoResponse> save(@Valid @RequestBody PilotoEntity pilotoEntity) {
        return pilotoService.save(pilotoEntity);
    }

    @Operation(summary = "Buscar piloto por ID", description = "Obtiene la informacion de un piloto usando su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Piloto encontrado"),
            @ApiResponse(responseCode = "404", description = "Piloto no encontrado")
    })
    @GetMapping("/find/{id}")
    public ResponseBase<PilotoResponse> findById(
            @Parameter(description = "ID del piloto", example = "1")
            @PathVariable Integer id) {
        return pilotoService.findById(id);
    }

    @Operation(summary = "Listar pilotos", description = "Obtiene todos los pilotos registrados.")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @GetMapping("/find/all")
    public List<PilotoResponse> findAll() {
        return pilotoService.findAll();
    }
}
