package com.codigo.msregisterhexagonal.infrastructure.repository.mongo;

import com.codigo.msregisterhexagonal.infrastructure.entity.PersonEntityDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepositoryDoc extends MongoRepository<PersonEntityDoc, Long> {
    Optional<PersonEntityDoc> findByNumDoc(String numDoc);
}
