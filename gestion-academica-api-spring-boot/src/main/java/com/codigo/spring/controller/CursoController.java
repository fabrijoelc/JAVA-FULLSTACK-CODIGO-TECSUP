package com.codigo.spring.controller;

import com.codigo.spring.request.CursoRequest;
import com.codigo.spring.request.CursoUpdateRequest;
import com.codigo.spring.response.CursoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/curso")
@Tag(name = "Curso", description = "Endpoints para crear, consultar y actualizar cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping("/save")
    @Operation(summary = "Crear curso", description = "Registra un curso relacionando un profesor y una asignatura existentes.")
    public ResponseBase<CursoResponse> saveCurso(@Valid @RequestBody CursoRequest request) {
        return cursoService.save(request);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Consultar curso", description = "Busca un curso por su id y devuelve profesor y asignatura resumidos.")
    public ResponseBase<CursoResponse> getById(@PathVariable int id) {
        return cursoService.getById(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualizar curso", description = "Actualiza el profesor y la asignatura asociados a un curso.")
    public ResponseBase<CursoResponse> updateCurso(
            @PathVariable int id,
            @Valid @RequestBody CursoUpdateRequest request) {
        return cursoService.update(id, request);
    }
}
