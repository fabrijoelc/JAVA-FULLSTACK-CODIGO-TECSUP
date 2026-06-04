package com.codigo.spring.controller;

import com.codigo.spring.request.AsignaturaRequest;
import com.codigo.spring.response.AsignaturaResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.AsignaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asignatura")
@Tag(name = "Asignatura", description = "Endpoints para crear y consultar asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @PostMapping("/save")
    @Operation(summary = "Crear asignatura", description = "Registra una asignatura con nombre y descripcion.")
    public ResponseBase<AsignaturaResponse> save(@Valid @RequestBody AsignaturaRequest request) {
        AsignaturaResponse saved = asignaturaService.save(request);
        return new ResponseBase<>(200, "Asignatura guardada correctamente", saved);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Consultar asignatura", description = "Busca una asignatura por su id.")
    public ResponseBase<AsignaturaResponse> getById(@PathVariable int id) {
        Optional<AsignaturaResponse> optional = asignaturaService.getById(id);
        if (optional.isPresent()) {
            return new ResponseBase<>(200, "Asignatura encontrada", optional.get());
        } else {
            return new ResponseBase<>(404, "Asignatura no encontrada", null);
        }
    }
}
