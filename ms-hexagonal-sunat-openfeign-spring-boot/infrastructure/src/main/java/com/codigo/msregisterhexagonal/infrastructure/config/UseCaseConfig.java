package com.codigo.msregisterhexagonal.infrastructure.config;

import com.codigo.msregisterhexagonal.domain.ports.in.EmpresaServiceIn;
import com.codigo.msregisterhexagonal.domain.ports.out.EmpresaServiceOut;
import com.codigo.msregisterhexagonal.domain.ports.out.SunatServiceOut;
import com.codigo.msregisterhexagonal.domain.usecase.EmpresaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public EmpresaServiceIn empresaServiceIn(EmpresaServiceOut empresaServiceOut, SunatServiceOut sunatServiceOut) {
        return new EmpresaServiceImpl(empresaServiceOut, sunatServiceOut);
    }
}
