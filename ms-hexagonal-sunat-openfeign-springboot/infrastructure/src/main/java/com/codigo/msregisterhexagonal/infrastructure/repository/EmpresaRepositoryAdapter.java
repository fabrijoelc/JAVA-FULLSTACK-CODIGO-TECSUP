package com.codigo.msregisterhexagonal.infrastructure.repository;

import com.codigo.msregisterhexagonal.domain.model.Empresa;
import com.codigo.msregisterhexagonal.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregisterhexagonal.infrastructure.entity.EmpresaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpresaRepositoryAdapter implements EmpresaServiceOut {

    private final EmpresaRepository empresaRepository;

    public EmpresaRepositoryAdapter(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        EmpresaEntity empresaEntity = toEntity(empresa);
        EmpresaEntity empresaGuardada = empresaRepository.save(empresaEntity);
        return toDomain(empresaGuardada);
    }

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Empresa> listar() {
        return empresaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Empresa actualizar(Long id, Empresa empresa) {
        empresa.setId(id);
        EmpresaEntity empresaEntity = toEntity(empresa);
        EmpresaEntity empresaActualizada = empresaRepository.save(empresaEntity);
        return toDomain(empresaActualizada);
    }

    @Override
    public void eliminar(Long id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaEntity toEntity(Empresa empresa) {
        return EmpresaEntity.builder()
                .id(empresa.getId())
                .razonSocial(empresa.getRazonSocial())
                .numeroDocumento(empresa.getNumeroDocumento())
                .estado(empresa.getEstado())
                .condicion(empresa.getCondicion())
                .direccion(empresa.getDireccion())
                .departamento(empresa.getDepartamento())
                .provincia(empresa.getProvincia())
                .distrito(empresa.getDistrito())
                .actividadEconomica(empresa.getActividadEconomica())
                .numeroTrabajadores(empresa.getNumeroTrabajadores())
                .build();
    }

    private Empresa toDomain(EmpresaEntity empresaEntity) {
        return Empresa.builder()
                .id(empresaEntity.getId())
                .razonSocial(empresaEntity.getRazonSocial())
                .numeroDocumento(empresaEntity.getNumeroDocumento())
                .estado(empresaEntity.getEstado())
                .condicion(empresaEntity.getCondicion())
                .direccion(empresaEntity.getDireccion())
                .departamento(empresaEntity.getDepartamento())
                .provincia(empresaEntity.getProvincia())
                .distrito(empresaEntity.getDistrito())
                .actividadEconomica(empresaEntity.getActividadEconomica())
                .numeroTrabajadores(empresaEntity.getNumeroTrabajadores())
                .build();
    }
}
