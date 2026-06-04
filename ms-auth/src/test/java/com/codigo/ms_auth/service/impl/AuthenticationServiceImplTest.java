package com.codigo.ms_auth.service.impl;

import com.codigo.ms_auth.aggregates.request.SignInRequest;
import com.codigo.ms_auth.aggregates.request.SignUpRequest;
import com.codigo.ms_auth.aggregates.response.SignInResponse;
import com.codigo.ms_auth.aggregates.response.SignUpResponse;
import com.codigo.ms_auth.entity.Usuario;
import com.codigo.ms_auth.entity.Rol;
import com.codigo.ms_auth.repository.RolRepository;
import com.codigo.ms_auth.repository.UsuarioRepository;
import com.codigo.ms_auth.service.JwtService;
import com.codigo.ms_auth.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignIn_ReturnsTokenWhenCredentialsValid() {
        // Arrange
        SignInRequest request = new SignInRequest("usuario", "123456");

        Usuario usuario = mock(Usuario.class);
        when(usuarioRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(usuario));
        when(usuario.getRoles()).thenReturn(mockRoles());

        // Mock para UserDetailsService
        UserDetailsService userDetailsServiceMock = mock(UserDetailsService.class);
        when(usuarioService.userDetailsService()).thenReturn(userDetailsServiceMock);

        // Autenticación simulada correctamente
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mock(Authentication.class));

        // Token generado
        when(jwtService.generateToken(usuario)).thenReturn("mocked-jwt-token");

        // Act
        SignInResponse response = authenticationService.signIn(request);

        // Assert
        assertEquals("mocked-jwt-token", response.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testSignUp_NormalizesUserRoleToUsuario() {
        SignUpRequest request = new SignUpRequest(
                "Rosa", "Perez", "rosa@mail.com", "123456",
                "DNI", "12345678", "USER"
        );
        Rol rolUsuario = Rol.builder().id(1L).nombre(Rol.USUARIO).build();

        when(rolRepository.findByNombre(Rol.USUARIO)).thenReturn(Optional.empty());
        when(rolRepository.save(any(Rol.class))).thenReturn(rolUsuario);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SignUpResponse response = authenticationService.signUp(request);

        assertEquals("rosa@mail.com", response.getEmail());
        verify(rolRepository).save(argThat(rol -> Rol.USUARIO.equals(rol.getNombre())));
        verify(usuarioRepository).save(argThat(usuario ->
                usuario.getRoles() != null &&
                        usuario.getRoles().stream().anyMatch(rol -> Rol.USUARIO.equals(rol.getNombre()))
        ));
    }


    @Test
    void testValidateToken_ValidTokenReturnsTrue() {
        String token = "valid.token.here";

        // 👇 Simular que se obtiene un UserDetails válido
        when(usuarioService.userDetailsService()).thenReturn(userDetailsService);
        when(jwtService.extractUserName(token)).thenReturn("usuario@email.com");
        when(userDetailsService.loadUserByUsername("usuario@email.com")).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(true);

        // Act
        boolean result = authenticationService.validateToken(token);

        // Assert
        assertTrue(result);
    }

    @Test
    void testValidateToken_InvalidTokenReturnsFalse() {
        String token = "invalid.token.here";

        // 👇 Simular token inválido
        when(usuarioService.userDetailsService()).thenReturn(userDetailsService);
        when(jwtService.extractUserName(token)).thenReturn("usuario@email.com");
        when(userDetailsService.loadUserByUsername("usuario@email.com")).thenReturn(userDetails);
        when(jwtService.validateToken(token, userDetails)).thenReturn(false);

        // Act
        boolean result = authenticationService.validateToken(token);

        // Assert
        assertFalse(result);
    }

    @Test
    void testValidateToken_WhenExceptionReturnsFalse() {
        String token = "broken.token";
        when(jwtService.extractUserName(token)).thenThrow(new RuntimeException("Token roto"));

        assertFalse(authenticationService.validateToken(token));
    }

    @Test
    void testTodos_ReturnsRepositoryUsers() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        assertEquals(1, authenticationService.todos().size());
    }

    @Test
    void testValidate_ReturnsUserDataWhenTokenValid() {
        Usuario usuario = usuarioConRol(Rol.ADMIN);
        when(jwtService.extractUserName("token")).thenReturn(usuario.getEmail());
        when(usuarioService.userDetailsService()).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername(usuario.getEmail())).thenReturn(usuario);
        when(jwtService.validateToken("token", usuario)).thenReturn(true);
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        ResponseEntity<?> response = authenticationService.validate("Bearer token");

        assertEquals(200, response.getStatusCodeValue());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals(usuario.getEmail(), body.get("email"));
        assertEquals(usuario.getId(), body.get("id"));
    }

    @Test
    void testValidate_ReturnsUnauthorizedWhenTokenInvalid() {
        when(jwtService.extractUserName("token")).thenReturn("usuario@mail.com");
        when(usuarioService.userDetailsService()).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername("usuario@mail.com")).thenReturn(userDetails);
        when(jwtService.validateToken("token", userDetails)).thenReturn(false);

        ResponseEntity<?> response = authenticationService.validate("Bearer token");

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    void testGetTokenByRefreshToken_ReturnsNewAccessToken() throws Exception {
        Usuario usuario = usuarioConRol(Rol.USUARIO);
        when(jwtService.validateIsRefreshToken("refresh-token")).thenReturn(true);
        when(jwtService.extractUserName("refresh-token")).thenReturn(usuario.getEmail());
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        when(usuarioService.userDetailsService()).thenReturn(userDetailsService);
        when(userDetailsService.loadUserByUsername(usuario.getEmail())).thenReturn(usuario);
        when(jwtService.validateToken("refresh-token", usuario)).thenReturn(true);
        when(jwtService.generateToken(usuario)).thenReturn("new-access-token");

        SignInResponse response = authenticationService.getTokenByRefreshToken("refresh-token");

        assertEquals("new-access-token", response.getToken());
        assertEquals(List.of(Rol.USUARIO), response.getRoles());
    }

    @Test
    void testGetTokenByRefreshToken_WhenAccessTokenThrows() {
        when(jwtService.validateIsRefreshToken("access-token")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authenticationService.getTokenByRefreshToken("access-token"));
    }

    private Set<Rol> mockRoles() {
        Rol rol = new Rol();
        rol.setNombre("USUARIO");

        return new HashSet<>(Collections.singleton(rol));
    }

    private Usuario usuarioConRol(String rolNombre) {
        Rol rol = Rol.builder().id(1L).nombre(rolNombre).build();
        return Usuario.builder()
                .id(1L)
                .nombres("Ana")
                .apellidos("Lopez")
                .email("ana@mail.com")
                .password("secret")
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Set.of(rol))
                .build();
    }
}
