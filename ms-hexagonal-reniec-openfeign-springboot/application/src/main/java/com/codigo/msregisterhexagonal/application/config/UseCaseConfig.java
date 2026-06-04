package com.codigo.msregisterhexagonal.application.config;

import com.codigo.msregisterhexagonal.domain.ports.in.PersonServiceIn;
import com.codigo.msregisterhexagonal.domain.ports.out.PersonServiceOut;
import com.codigo.msregisterhexagonal.domain.usecase.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PersonServiceIn personServiceIn(PersonServiceOut personServiceOut) {
        return new PersonServiceImpl(personServiceOut);
    }
}
