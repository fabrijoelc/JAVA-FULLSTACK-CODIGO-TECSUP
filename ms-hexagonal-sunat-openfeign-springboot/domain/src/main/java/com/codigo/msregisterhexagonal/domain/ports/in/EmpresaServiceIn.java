package com.codigo.msregisterhexagonal.domain.ports.in;

import com.codigo.msregisterhexagonal.domain.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {

    Empresa guardar(Empresa empresa);

    Empresa guardarDesdeSunat(String ruc);

    Optional<Empresa> buscarPorId(Long id);

    List<Empresa> listar();

    Empresa actualizar(Long id, Empresa empresa);

    void eliminar(Long id);
}
