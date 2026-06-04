package com.codigo.ms_auth.controller;

import com.codigo.ms_auth.entity.Rol;
import com.codigo.ms_auth.entity.Usuario;
import com.codigo.ms_auth.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSuperAdmins_listaPorRol() {
        when(usuarioRepository.findByRoles_Nombre(Rol.SUPERADMIN)).thenReturn(List.of(new Usuario()));

        ResponseEntity<?> response = adminController.getSuperAdmins();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    void getAdmins_listaPorRol() {
        when(usuarioRepository.findByRoles_Nombre(Rol.ADMIN)).thenReturn(List.of(new Usuario()));

        ResponseEntity<?> response = adminController.getAdmins();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    void getUsers_listaPorRol() {
        when(usuarioRepository.findByRoles_Nombre(Rol.USUARIO)).thenReturn(List.of(new Usuario()));

        ResponseEntity<?> response = adminController.getUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, ((List<?>) response.getBody()).size());
    }
}
