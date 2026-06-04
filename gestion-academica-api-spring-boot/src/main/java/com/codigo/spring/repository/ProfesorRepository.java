package com.codigo.spring.repository;

import com.codigo.spring.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, String> {
}
