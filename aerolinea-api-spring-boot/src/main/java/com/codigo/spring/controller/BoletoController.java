package com.codigo.spring.controller;

import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.BoletoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boleto")
@Tag(name = "Boletos", description = "Endpoints para registrar boletos de pasajeros en vuelos")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @Operation(summary = "Registrar un boleto", description = "Registra un boleto para un pasajero en un vuelo, validando que el asiento no este ocupado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleto registrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Vuelo o pasajero no encontrados"),
            @ApiResponse(responseCode = "409", description = "El asiento ya esta ocupado")
    })
    @PostMapping("/save")
    public ResponseBase<BoletoResponse> save(@Valid @RequestBody BoletoRequest boletoRequest){
        return boletoService.save(boletoRequest);
    }
}
