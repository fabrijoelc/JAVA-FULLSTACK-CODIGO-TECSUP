package com.codigo.ms_productos.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthValidatorTest {

    private RestTemplate restTemplate;
    private AuthValidator authValidator;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        authValidator = new AuthValidator(restTemplate);
        ReflectionTestUtils.setField(authValidator, "urlValidate", "http://localhost:8080/auth/validate");
    }

    @Test
    void tieneAcceso_conRolAdmin_retornaTrue() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{\"id\":1,\"email\":\"admin@mail.com\",\"roles\":[\"ADMIN\"]}"));

        assertTrue(authValidator.tieneAcceso("Bearer token"));
        assertTrue(authValidator.tokenValido("Bearer token"));
    }

    @Test
    void tieneAcceso_conRolUsuario_retornaFalsePeroTokenValido() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(ResponseEntity.ok("{\"id\":2,\"email\":\"user@mail.com\",\"roles\":[\"USUARIO\"]}"));

        assertFalse(authValidator.tieneAcceso("Bearer token"));
        assertTrue(authValidator.tokenValido("Bearer token"));
    }
}
