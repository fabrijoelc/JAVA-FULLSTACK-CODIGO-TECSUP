package com.codigo.spring.repository;

import com.codigo.spring.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {
}
