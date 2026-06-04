package com.codigo.spring.repository;

import com.codigo.spring.entity.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Integer> {
}