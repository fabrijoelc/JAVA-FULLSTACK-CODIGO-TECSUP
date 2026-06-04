package com.codigo.apis_externas.mapper;

import com.codigo.apis_externas.aggregates.constants.Constants;
import com.codigo.apis_externas.aggregates.response.ReniecResponse;
import com.codigo.apis_externas.entity.PersonEntity;

import java.sql.Timestamp;

public class PersonMapper {

    private PersonMapper() {
    }

    public static PersonEntity fromReniecResponse(ReniecResponse reniecResponse) {
        return PersonEntity.builder()
                .names(reniecResponse.getNombres())
                .fullName(reniecResponse.getNombreCompleto())
                .lastName(reniecResponse.getApellidoPaterno())
                .motherLastName(reniecResponse.getApellidoMaterno())
                .numberDocument(reniecResponse.getNumeroDocumento())
                .status(Constants.STATUS_ACTIVE)
                .userCreated(Constants.USER_ADMIN)
                .dateCreated(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
