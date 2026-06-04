package com.codigo.spring.repository;

import com.codigo.spring.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Integer> {
}

