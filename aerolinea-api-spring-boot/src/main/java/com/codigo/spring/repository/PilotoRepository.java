package com.codigo.spring.repository;

import com.codigo.spring.entity.PilotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PilotoRepository extends JpaRepository<PilotoEntity, Integer> {
    List<PilotoEntity> findByIdIn(List<Integer> ids);
}
