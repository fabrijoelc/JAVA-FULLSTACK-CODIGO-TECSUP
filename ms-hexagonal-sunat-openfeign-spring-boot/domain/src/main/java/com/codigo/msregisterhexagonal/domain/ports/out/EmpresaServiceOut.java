package com.codigo.msregisterhexagonal.domain.ports.out;

import com.codigo.msregisterhexagonal.domain.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {

    Empresa guardar(Empresa empresa);

    Optional<Empresa> buscarPorId(Long id);

    List<Empresa> listar();

    Empresa actualizar(Long id, Empresa empresa);

    void eliminar(Long id);
}
