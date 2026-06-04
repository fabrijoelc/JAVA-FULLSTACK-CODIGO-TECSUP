package com.codigo.msregisterhexagonal.domain.usecase;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDTO;
import com.codigo.msregisterhexagonal.domain.ports.in.PersonServiceIn;
import com.codigo.msregisterhexagonal.domain.ports.out.PersonServiceOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class PersonServiceImpl implements PersonServiceIn {

    private final PersonServiceOut personServiceOut;

    private String SERVICE_NAME = "PersonServiceImpl";
    @Override
    public PersonDTO createPersonIn(String dni) {
        String nameMethod = "createPersonIn";
        log.info("{} - {} - INICIO",SERVICE_NAME,nameMethod);
        PersonDTO personDTO = personServiceOut.createPersonOut(dni);
        log.info("{} - {} - FIN",SERVICE_NAME,nameMethod);
        return personDTO;
    }

    @Override
    public List<PersonDTO> getAllPersonsIn() {
        String nameMethod = "getAllPersonsIn";
        log.info("{} - {} - INICIO", SERVICE_NAME, nameMethod);
        List<PersonDTO> persons = personServiceOut.getAllPersonsOut();
        log.info("{} - {} - FIN", SERVICE_NAME, nameMethod);
        return persons;
    }

    @Override
    public PersonDTO getPersonByDniIn(String dni) {
        String nameMethod = "getPersonByDniIn";
        log.info("{} - {} - INICIO", SERVICE_NAME, nameMethod);
        PersonDTO personDTO = personServiceOut.getPersonByDniOut(dni);
        log.info("{} - {} - FIN", SERVICE_NAME, nameMethod);
        return personDTO;
    }

    @Override
    public void deletePersonByDniIn(String dni) {
        String nameMethod = "deletePersonByDniIn";
        log.info("{} - {} - INICIO", SERVICE_NAME, nameMethod);
        personServiceOut.deletePersonByDniOut(dni);
        log.info("{} - {} - FIN", SERVICE_NAME, nameMethod);
    }
}
