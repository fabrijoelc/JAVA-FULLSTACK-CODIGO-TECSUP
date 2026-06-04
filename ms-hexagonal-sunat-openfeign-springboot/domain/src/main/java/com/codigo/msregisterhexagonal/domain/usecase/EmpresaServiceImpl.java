package com.codigo.msregisterhexagonal.domain.usecase;

import com.codigo.msregisterhexagonal.domain.model.Empresa;
import com.codigo.msregisterhexagonal.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregisterhexagonal.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregisterhexagonal.domain.ports.out.SunatServiceOut;

import java.util.List;
import java.util.Optional;

public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;
    private final SunatServiceOut sunatServiceOut;

    public EmpresaServiceImpl(EmpresaServiceOut empresaServiceOut, SunatServiceOut sunatServiceOut) {
        this.empresaServiceOut = empresaServiceOut;
        this.sunatServiceOut = sunatServiceOut;
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        return empresaServiceOut.guardar(empresa);
    }

    @Override
    public Empresa guardarDesdeSunat(String ruc) {
        Empresa empresa = sunatServiceOut.consultarPorRuc(ruc);
        return empresaServiceOut.guardar(empresa);
    }

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return empresaServiceOut.buscarPorId(id);
    }

    @Override
    public List<Empresa> listar() {
        return empresaServiceOut.listar();
    }

    @Override
    public Empresa actualizar(Long id, Empresa empresa) {
        empresa.setId(id);
        return empresaServiceOut.actualizar(id, empresa);
    }

    @Override
    public void eliminar(Long id) {
        empresaServiceOut.eliminar(id);
    }
}
