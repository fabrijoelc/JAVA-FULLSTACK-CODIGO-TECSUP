package com.codigo.msregisterhexagonal.infrastructure.adapters;

import com.codigo.msregisterhexagonal.domain.aggregates.dto.PersonDTO;
import com.codigo.msregisterhexagonal.domain.exception.PersonNotFoundException;
import com.codigo.msregisterhexagonal.domain.exception.ReniecException;
import com.codigo.msregisterhexagonal.domain.ports.out.PersonServiceOut;
import com.codigo.msregisterhexagonal.infrastructure.entity.PersonEntity;
import com.codigo.msregisterhexagonal.infrastructure.entity.PersonEntityDoc;
import com.codigo.msregisterhexagonal.infrastructure.repository.jpa.PersonRepository;
import com.codigo.msregisterhexagonal.infrastructure.repository.mongo.PersonRepositoryDoc;
import com.codigo.msregisterhexagonal.infrastructure.response.ResponseReniec;
import com.codigo.msregisterhexagonal.infrastructure.rest.ReniecClient;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class PersonAdapter implements PersonServiceOut {
    private final ReniecClient reniecClient;
    private final ModelMapper personMapper;
    private final ModelMapper reniecMapper;
    private final ModelMapper personDocMapper;
    private final PersonRepository personRepository;
    private final PersonRepositoryDoc repositoryDoc;


    @Value("${token.api}")
    private String token;

    public PersonAdapter(ReniecClient reniecClient,
                         @Qualifier("defaultMapper") ModelMapper personMapper,
                         @Qualifier("reniecMapper") ModelMapper reniecMapper,
                         @Qualifier("reniecMapperDoc") ModelMapper personDocMapper,
                         PersonRepository personRepository,
                         PersonRepositoryDoc repositoryDoc) {
        this.reniecClient = reniecClient;
        this.personMapper = personMapper;
        this.reniecMapper = reniecMapper;
        this.personDocMapper = personDocMapper;
        this.personRepository = personRepository;
        this.repositoryDoc = repositoryDoc;
    }

    @Override
    public PersonDTO createPersonOut(String dni) {
        validateDni(dni);
        ResponseReniec responseReniec = executeReniec(dni);

        if (responseReniec == null || responseReniec.getNumeroDocumento() == null) {
            throw new ReniecException("Respuesta invalida de RENIEC para el DNI: " + dni);
        }

        PersonEntity personSql = personRepository.save(getEntityForSaveSql(responseReniec));
        repositoryDoc.save(getEntityForSaveMongo(responseReniec));
        return mapToPersonDTO(personSql);
    }

    @Override
    public List<PersonDTO> getAllPersonsOut() {
        return personRepository.findAll()
                .stream()
                .map(this::mapToPersonDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonByDniOut(String dni) {
        validateDni(dni);
        return personRepository.findByNumDoc(dni)
                .map(this::mapToPersonDTO)
                .or(() -> repositoryDoc.findByNumDoc(dni).map(this::mapToPersonDTO))
                .orElseThrow(() -> new PersonNotFoundException(dni));
    }

    @Override
    public void deletePersonByDniOut(String dni) {
        validateDni(dni);
        PersonEntity person = personRepository.findByNumDoc(dni)
                .orElseThrow(() -> new PersonNotFoundException(dni));
        personRepository.delete(person);
        repositoryDoc.findByNumDoc(dni).ifPresent(repositoryDoc::delete);
    }

    private PersonEntity getEntityForSaveSql(ResponseReniec responseReniec) {
        log.info("Preparando persona para PostgreSQL con DNI: {}", responseReniec.getNumeroDocumento());
        PersonEntity person = mapReniecToPersonEntity(responseReniec);
        personRepository.findByNumDoc(responseReniec.getNumeroDocumento())
                .map(PersonEntity::getId)
                .ifPresent(person::setId);
        fillCommonFields(person, responseReniec);
        return person;
    }

    private PersonEntityDoc getEntityForSaveMongo(ResponseReniec responseReniec) {
        log.info("Preparando persona para MongoDB con DNI: {}", responseReniec.getNumeroDocumento());
        PersonEntityDoc person = mapReniecToPersonEntityDoc(responseReniec);
        repositoryDoc.findByNumDoc(responseReniec.getNumeroDocumento())
                .map(PersonEntityDoc::getId)
                .ifPresent(person::setId);
        fillCommonFields(person, responseReniec);
        return person;
    }

    private void fillCommonFields(PersonEntity person, ResponseReniec responseReniec) {
        person.setTypeDoc("DNI");
        person.setLastName(buildFullLastName(responseReniec));
        person.setStatus(1);
        person.setUserCreate("PRODRIGUEZ");
        person.setDateCreate(new Timestamp(System.currentTimeMillis()));
    }

    private void fillCommonFields(PersonEntityDoc person, ResponseReniec responseReniec) {
        person.setTypeDoc("DNI");
        person.setLastName(buildFullLastName(responseReniec));
        person.setStatus(1);
        person.setUserCreate("PRODRIGUEZ");
        person.setDateCreate(new Date());
    }

    private ResponseReniec executeReniec(String dni){
        log.info("Consultando los datos a RENIEC para el DNI: {}", dni);
        String header = "Bearer "+token;
        try {
            return Optional.ofNullable(reniecClient.getInfoReniec(dni, header))
                    .orElseThrow(() -> new ReniecException("Error al consultar con RENIEC"));
        } catch (ReniecException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new ReniecException("No se pudo consultar Decolecta/RENIEC para el DNI: " + dni, exception);
        }

    }
    private PersonDTO mapToPersonDTO(PersonEntityDoc person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setNumDocPerson(person.getNumDoc());
        personDTO.setTypeDocPerson(person.getTypeDoc());
        personDTO.setFirstNamePerson(person.getFirstName());
        personDTO.setLastNamePerson(person.getLastName());
        personDTO.setStatusPerson(person.getStatus());
        personDTO.setUserCreatePerson(person.getUserCreate());
        if (person.getDateCreate() != null) {
            personDTO.setDateCreatePerson(new Timestamp(person.getDateCreate().getTime()));
        }
        return personDTO;
    }

    private PersonDTO mapToPersonDTO(PersonEntity person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setNumDocPerson(person.getNumDoc());
        personDTO.setTypeDocPerson(person.getTypeDoc());
        personDTO.setFirstNamePerson(person.getFirstName());
        personDTO.setLastNamePerson(person.getLastName());
        personDTO.setStatusPerson(person.getStatus());
        personDTO.setUserCreatePerson(person.getUserCreate());
        personDTO.setDateCreatePerson(person.getDateCreate());
        return personDTO;
    }

    private PersonEntity mapReniecToPersonEntity(ResponseReniec responseReniec){
        return reniecMapper.map(responseReniec, PersonEntity.class);
    }

    private PersonEntityDoc mapReniecToPersonEntityDoc(ResponseReniec responseReniec){
        return personDocMapper.map(responseReniec, PersonEntityDoc.class);
    }

    private String buildFullLastName(ResponseReniec responseReniec) {
        return Stream.of(responseReniec.getApellidoPaterno(), responseReniec.getApellidoMaterno())
                .filter(value -> value != null && !value.isBlank())
                .collect(Collectors.joining(" "));
    }

    private void validateDni(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI es obligatorio");
        }
        if (!dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe tener 8 digitos numericos");
        }
    }
}
