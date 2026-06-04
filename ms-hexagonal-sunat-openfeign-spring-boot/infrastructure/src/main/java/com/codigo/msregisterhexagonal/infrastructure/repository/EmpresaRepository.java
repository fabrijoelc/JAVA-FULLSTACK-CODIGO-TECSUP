package com.codigo.msregisterhexagonal.infrastructure.repository;

import com.codigo.msregisterhexagonal.infrastructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {
}
