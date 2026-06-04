package com.codigo.spring.controller;

import com.codigo.spring.request.MatriculaRequest;
import com.codigo.spring.response.MatriculaResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/matricula")
@Tag(name = "Matricula", description = "Endpoints para crear y consultar matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/save")
    @Operation(summary = "Crear matricula", description = "Registra una matricula relacionando un estudiante y un curso existentes.")
    public ResponseBase<MatriculaResponse> save(@Valid @RequestBody MatriculaRequest request) {
        return matriculaService.save(request);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Consultar matricula", description = "Busca una matricula por su id.")
    public ResponseBase<MatriculaResponse> getById(@PathVariable int id) {
        return matriculaService.getById(id);
    }
}
