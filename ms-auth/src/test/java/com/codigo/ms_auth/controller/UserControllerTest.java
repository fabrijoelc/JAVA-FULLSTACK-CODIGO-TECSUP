package com.codigo.ms_auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserControllerTest {

    @Test
    void getUserProfile_retornaMensajePrivado() {
        UserController controller = new UserController();

        ResponseEntity<String> response = controller.getUserProfile();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola USER, este es tu perfil privado", response.getBody());
    }
}
