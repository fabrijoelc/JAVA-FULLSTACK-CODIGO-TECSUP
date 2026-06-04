package com.codigo.msregisterhexagonal.domain.ports.out;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDTO;

import java.util.List;

public interface PersonServiceOut {
    PersonDTO createPersonOut(String dni);
    List<PersonDTO> getAllPersonsOut();
    PersonDTO getPersonByDniOut(String dni);
    void deletePersonByDniOut(String dni);
}
