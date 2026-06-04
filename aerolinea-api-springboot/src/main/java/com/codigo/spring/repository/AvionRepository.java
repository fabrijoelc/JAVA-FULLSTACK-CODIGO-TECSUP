package com.codigo.spring.repository;

import com.codigo.spring.entity.AvionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvionRepository extends JpaRepository<AvionEntity, Integer> {
    List<AvionEntity> findByModelo(String modelo);
    List<AvionEntity> findByCapacidadBetween(int min, int max);
}
