package com.codigo.ms_auth.controller;

import com.codigo.ms_auth.entity.Rol;
import com.codigo.ms_auth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class AdminController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/superadmin")
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    public ResponseEntity<?> getSuperAdmins() {
        return ResponseEntity.ok(usuarioRepository.findByRoles_Nombre(Rol.SUPERADMIN));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<?> getAdmins() {
        return ResponseEntity.ok(usuarioRepository.findByRoles_Nombre(Rol.ADMIN));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USUARIO', 'ADMIN', 'SUPERADMIN')")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(usuarioRepository.findByRoles_Nombre(Rol.USUARIO));
    }
}
