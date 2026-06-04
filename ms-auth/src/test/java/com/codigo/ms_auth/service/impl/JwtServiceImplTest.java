package com.codigo.ms_auth.service.impl;

import com.codigo.ms_auth.entity.Rol;
import com.codigo.ms_auth.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {

    private JwtServiceImpl jwtService;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        jwtService = new JwtServiceImpl();
        String rawSecret = "1234567890123456789012345678901212345678901234567890123456789012";
        String encodedSecret = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(rawSecret.getBytes(StandardCharsets.UTF_8));
        ReflectionTestUtils.setField(jwtService, "secretKey", encodedSecret);

        Rol rol = Rol.builder().id(1L).nombre(Rol.USUARIO).build();
        usuario = Usuario.builder()
                .id(1L)
                .nombres("Ana")
                .apellidos("Lopez")
                .email("ana@mail.com")
                .numDoc("12345678")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Set.of(rol))
                .build();
    }

    @Test
    void generateToken_extractsUsernameAndValidates() {
        String token = jwtService.generateToken(usuario);

        assertEquals("ana@mail.com", jwtService.extractUserName(token));
        assertTrue(jwtService.validateToken(token, usuario));
        assertFalse(jwtService.validateIsRefreshToken(token));
    }

    @Test
    void generateRefreshToken_marksTokenAsRefresh() {
        String token = jwtService.generateRefreshToken(new HashMap<>(), usuario);

        assertEquals("ana@mail.com", jwtService.extractUserName(token));
        assertTrue(jwtService.validateToken(token, usuario));
        assertTrue(jwtService.validateIsRefreshToken(token));
    }
}
