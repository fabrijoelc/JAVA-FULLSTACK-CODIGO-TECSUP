package com.codigo.ms_ordenes.service.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    void validateToken_retornaUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(3L);
        usuario.setEmail("user@mail.com");
        usuario.setRoles(List.of("USUARIO"));

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(UsuarioDTO.class)))
                .thenReturn(ResponseEntity.ok(usuario));

        UsuarioDTO resultado = authValidator.validateToken("Bearer token");

        assertEquals(3L, resultado.getId());
        assertEquals("USUARIO", resultado.getRol());
    }

    @Test
    void validateToken_sinBody_lanzaExcepcion() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(UsuarioDTO.class)))
                .thenReturn(ResponseEntity.ok(null));

        assertThrows(RuntimeException.class, () -> authValidator.validateToken("Bearer token"));
    }
}
