package com.codigo.apis_externas.service.impl;

import com.codigo.apis_externas.aggregates.constants.Constants;
import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.aggregates.response.ResponseBase;
import com.codigo.apis_externas.client.ClientReniec;
import com.codigo.apis_externas.entity.PersonEntity;
import com.codigo.apis_externas.exception.ConsultaReniecException;
import com.codigo.apis_externas.mapper.PersonMapper;
import com.codigo.apis_externas.repository.PersonRepository;
import com.codigo.apis_externas.service.PersonService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final ClientReniec clientReniec;
    private final PersonRepository personRepository;

    @Value("${value.token}")
    private String token;

    @Override
    public ResponseBase<ReniecResponse> findByDni(String dni) {
        log.info("Buscando informacion para el DNI: {}", dni);
        try {
            ReniecResponse reniecResponse = executionReniec(dni);
            return buildResponse(2004, "Todo OK!!", Optional.ofNullable(reniecResponse));
        } catch (ConsultaReniecException | FeignException exception) {
            log.error("No se pudo consultar Decolecta RENIEC para el DNI: {}", dni, exception);
            throw new ConsultaReniecException("No se pudo consultar Decolecta RENIEC: "
                    + exception.getMessage(), exception);
        }
    }

    private ReniecResponse executionReniec(String dni) {
        log.info("Ejecutando consulta a Decolecta RENIEC API para el DNI: {}", dni);
        if (token == null || token.isBlank()) {
            throw new ConsultaReniecException("Configura la variable de entorno DECOLECTA_TOKEN");
        }
        String tokenOk = "Bearer " + token;
        return clientReniec.getPerson(dni, tokenOk);
    }

    @Override
    public ResponseBase<PersonEntity> registerPerson(String dni) {
        log.info("Registrando Persona con DNI: {}", dni);

        ReniecResponse reniecResponse;

        try {
            reniecResponse = executionReniec(dni);
        } catch (ConsultaReniecException | FeignException exception) {
            log.error("No se pudo consultar Decolecta RENIEC para registrar el DNI: {}", dni, exception);
            throw new ConsultaReniecException("No se pudo consultar Decolecta RENIEC: "
                    + exception.getMessage(), exception);
        }

        if (reniecResponse == null) {
            return buildResponse(
                    4000,
                    "Ocurrio un Error no existe respuesta de Decolecta RENIEC!!",
                    Optional.empty()
            );
        }

        PersonEntity personEntity = PersonMapper.fromReniecResponse(reniecResponse);
        PersonEntity personSave = personRepository.save(personEntity);
        return buildResponse(2001, "Todo OK!!", Optional.of(personSave));
    }

    @Override
    public ResponseBase<List<PersonEntity>> findPersonActive() {
        List<PersonEntity> listPersonActive =
                personRepository.findAllByStatus(Constants.STATUS_ACTIVE);
        return buildResponse(2001, "Todo OK!!", Optional.of(listPersonActive));
    }

    private <T> ResponseBase<T> buildResponse(
            int code, String message, Optional<T> optional) {
        ResponseBase<T> responseBase = new ResponseBase<>();
        responseBase.setCode(code);
        responseBase.setMessage(message);
        responseBase.setEntity(optional);
        return responseBase;
    }
}
