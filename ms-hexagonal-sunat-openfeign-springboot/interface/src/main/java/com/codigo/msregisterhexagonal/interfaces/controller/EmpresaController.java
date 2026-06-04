package com.codigo.msregisterhexagonal.interfaces.controller;

import com.codigo.msregisterhexagonal.domain.model.Empresa;
import com.codigo.msregisterhexagonal.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregisterhexagonal.interfaces.dto.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    public EmpresaController(EmpresaServiceIn empresaServiceIn) {
        this.empresaServiceIn = empresaServiceIn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBase<Empresa> guardar(@RequestBody Empresa empresa) {
        Empresa empresaGuardada = empresaServiceIn.guardar(empresa);
        return ResponseBase.success(
                HttpStatus.CREATED.value(),
                "Empresa guardada correctamente",
                empresaGuardada
        );
    }

    @PostMapping("/sunat/{ruc}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBase<Empresa> guardarDesdeSunat(@PathVariable String ruc) {
        Empresa empresaGuardada = empresaServiceIn.guardarDesdeSunat(ruc);
        return ResponseBase.success(
                HttpStatus.CREATED.value(),
                "Empresa guardada desde SUNAT con OpenFeign",
                empresaGuardada
        );
    }

    @GetMapping("/{id}")
    public ResponseBase<Empresa> buscarPorId(@PathVariable Long id) {
        Empresa empresa = empresaServiceIn.buscarPorId(id)
                .orElseThrow(NoSuchElementException::new);
        return ResponseBase.success(
                HttpStatus.OK.value(),
                "Empresa encontrada correctamente",
                empresa
        );
    }

    @GetMapping
    public ResponseBase<List<Empresa>> listar() {
        List<Empresa> empresas = empresaServiceIn.listar();
        return ResponseBase.success(
                HttpStatus.OK.value(),
                "Empresas listadas correctamente",
                empresas
        );
    }

    @PutMapping("/{id}")
    public ResponseBase<Empresa> actualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa empresaActualizada = empresaServiceIn.actualizar(id, empresa);
        return ResponseBase.success(
                HttpStatus.OK.value(),
                "Empresa actualizada correctamente",
                empresaActualizada
        );
    }

    @DeleteMapping("/{id}")
    public ResponseBase<Object> eliminar(@PathVariable Long id) {
        empresaServiceIn.eliminar(id);
        return ResponseBase.success(
                HttpStatus.OK.value(),
                "Empresa eliminada correctamente",
                null
        );
    }
}
