package com.codigo.ms_ordenes.service.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


@Component
@RequiredArgsConstructor
public class AuthValidator {

    @Value("${ms.auth.validate}")
    private String urlValidate; // por ejemplo: http://localhost:8081/auth/validate

    private final RestTemplate restTemplate;

    public UsuarioDTO validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UsuarioDTO> response = restTemplate.exchange(
                urlValidate,
                HttpMethod.GET,
                entity,
                UsuarioDTO.class
        );

        UsuarioDTO usuario = response.getBody();
        if (!response.getStatusCode().is2xxSuccessful() || usuario == null) {
            throw new RuntimeException("Token invalido");
        }

        return usuario;
    }
}


