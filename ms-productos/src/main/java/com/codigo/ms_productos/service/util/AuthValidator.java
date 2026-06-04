package com.codigo.ms_productos.service.util;

import com.codigo.ms_productos.aggregates.constants.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthValidator {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ms.auth.validate:" + Constants.URL_VALIDATE_TOKEN + "}")
    private String urlValidate;

    private static final List<String> ROLES_PERMITIDOS = Arrays.asList(
            Constants.ROLE_ADMIN,
            Constants.ROLE_SUPERADMIN
    );

    public AuthValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public boolean tokenValido(String token) {
        return validar(token) != null;
    }

    public boolean tieneAcceso(String token) {
        JsonNode json = validar(token);
        if (json == null) {
            return false;
        }

        JsonNode rolesNode = json.get("roles");
        if (rolesNode != null && rolesNode.isArray()) {
            for (JsonNode rol : rolesNode) {
                if (ROLES_PERMITIDOS.contains(rol.asText())) {
                    return true;
                }
            }
        }
        return false;
    }

    private JsonNode validar(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token.replace("Bearer ", ""));
            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    urlValidate,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return objectMapper.readTree(response.getBody());
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
