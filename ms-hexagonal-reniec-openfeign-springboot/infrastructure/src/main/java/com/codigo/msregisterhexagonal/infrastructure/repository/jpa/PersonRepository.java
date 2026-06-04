package com.codigo.msregisterhexagonal.infrastructure.repository.jpa;

import com.codigo.msregisterhexagonal.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByNumDoc(String numDoc);
}
