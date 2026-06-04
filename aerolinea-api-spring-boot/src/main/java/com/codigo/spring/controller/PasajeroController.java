package com.codigo.spring.controller;

import com.codigo.spring.entity.PasajeroEntity;
import com.codigo.spring.response.PasajeroInfoResponse;
import com.codigo.spring.response.PasajeroResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.PasajeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pasajero")
@Tag(name = "Pasajeros", description = "Endpoints para registrar y consultar pasajeros")
public class PasajeroController {

    private final PasajeroService pasajeroService;

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @Operation(summary = "Registrar un pasajero", description = "Registra un pasajero en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Pasajero registrado correctamente")
    @PostMapping("/save")
    public ResponseBase<PasajeroResponse> save(@Valid @RequestBody PasajeroEntity pasajeroEntity) {
        return pasajeroService.save(pasajeroEntity);
    }

    @Operation(summary = "Buscar pasajero por ID", description = "Obtiene la informacion basica de un pasajero usando su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pasajero encontrado"),
            @ApiResponse(responseCode = "404", description = "Pasajero no encontrado")
    })
    @GetMapping("/find/{id}")
    public ResponseBase<PasajeroResponse> findById(
            @Parameter(description = "ID del pasajero", example = "1")
            @PathVariable Integer id) {
        return pasajeroService.findById(id);
    }

    @Operation(summary = "Buscar boletos de un pasajero", description = "Obtiene la informacion del pasajero junto con los boletos asociados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informacion encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PasajeroInfoResponse.class),
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"code\": 200,\n" +
                                    "  \"message\": \"Datos encontrados correctamente.\",\n" +
                                    "  \"data\": {\n" +
                                    "    \"nombre\": \"Fabrizio\",\n" +
                                    "    \"apellido\": \"Allcca\",\n" +
                                    "    \"boletos\": [\n" +
                                    "      {\n" +
                                    "        \"origen\": \"Lima\",\n" +
                                    "        \"destino\": \"Cusco\",\n" +
                                    "        \"asiento\": 12\n" +
                                    "      }\n" +
                                    "    ]\n" +
                                    "  }\n" +
                                    "}")
                    )),
            @ApiResponse(responseCode = "404", description = "Pasajero no encontrado")
    })
    @GetMapping("/find/boleto/{id}")
    public ResponseBase<PasajeroInfoResponse> findBoleto(
            @Parameter(description = "ID del pasajero", example = "1")
            @PathVariable Integer id) {
        return pasajeroService.findBoleto(id);
    }
}
