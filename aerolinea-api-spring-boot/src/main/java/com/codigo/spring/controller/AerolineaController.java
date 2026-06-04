package com.codigo.spring.controller;

import com.codigo.spring.entity.AerolineaEntity;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AerolineaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aerolinea")
@Tag(name = "Aerolineas", description = "Endpoints para registrar, consultar y actualizar aerolineas")
public class AerolineaController {

    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService){
        this.aerolineaService = aerolineaService;
    }

    @Operation(summary = "Registrar una aerolinea", description = "Registra una aerolinea en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Aerolinea registrada correctamente")
    @PostMapping("/save")
    public AerolineaEntity save(@Valid @RequestBody AerolineaEntity aerolineaEntity){
        return aerolineaService.save(aerolineaEntity);
    }

    @Operation(summary = "Listar aerolineas", description = "Obtiene todas las aerolineas registradas.")
    @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    @GetMapping("/find/all")
    public List<AerolineaEntity> findAll() {
        return aerolineaService.findAll();
    }

    @Operation(summary = "Buscar aerolinea por ID", description = "Obtiene una aerolinea usando su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aerolinea encontrada"),
            @ApiResponse(responseCode = "404", description = "Aerolinea no encontrada")
    })
    @GetMapping("/find/{id}")
    public ResponseBase<AerolineaEntity> findById(
            @Parameter(description = "ID de la aerolinea", example = "1")
            @PathVariable int id) {
        return aerolineaService.findById(id);
    }

    @Operation(summary = "Actualizar aerolinea", description = "Actualiza los datos de una aerolinea existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aerolinea actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Aerolinea no encontrada")
    })
    @PutMapping("/update/{id}")
    public ResponseBase<AerolineaEntity> update(
            @Parameter(description = "ID de la aerolinea", example = "1")
            @PathVariable int id,
            @Valid @RequestBody AerolineaEntity aerolineaEntity){
        aerolineaEntity.setId(id);
        return aerolineaService.updateById(aerolineaEntity);
    }
}
