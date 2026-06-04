package com.codigo.msregisterhexagonal.domain.ports.in;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDTO;

import java.util.List;

public interface PersonServiceIn {
    PersonDTO createPersonIn(String dni);
    List<PersonDTO> getAllPersonsIn();
    PersonDTO getPersonByDniIn(String dni);
    void deletePersonByDniIn(String dni);
}
