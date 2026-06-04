package com.codigo.ms_ordenes.serviceImpl;

import com.codigo.ms_ordenes.entity.Orden;
import com.codigo.ms_ordenes.repository.OrdenRepository;
import com.codigo.ms_ordenes.service.util.AuthValidator;
import com.codigo.ms_ordenes.service.util.UsuarioDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdenServiceImplTest {

    @Mock
    private OrdenRepository ordenRepository;

    @Mock
    private AuthValidator authValidator;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrdenServiceImpl ordenService;

    public OrdenServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void listarOrdenes_conRolAdmin_retornaLista() {
        // Arrange
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setRoles(List.of("ADMIN"));
        when(authValidator.validateToken("token")).thenReturn(usuario);

        List<Orden> ordenes = List.of(new Orden(), new Orden());
        when(ordenRepository.findAll()).thenReturn(ordenes);

        // Act
        List<Orden> resultado = ordenService.listarOrdenes("token");

        // Assert
        assertEquals(2, resultado.size());
    }

    @Test
    void crearOrden_conRolUsuarioYProductosValidos_guardaOrden() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(7L);
        usuario.setRoles(List.of("USUARIO"));
        when(authValidator.validateToken("token")).thenReturn(usuario);

        Orden orden = Orden.builder()
                .productosIds(List.of(1L, 2L))
                .build();
        Orden guardada = Orden.builder()
                .id(10L)
                .usuarioId(7L)
                .productosIds(List.of(1L, 2L))
                .fecha(LocalDateTime.now())
                .build();

        ReflectionTestUtils.setField(ordenService, "productosUrl", "http://localhost:8081/productos");
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Boolean.class)
        )).thenReturn(ResponseEntity.ok(true));
        when(ordenRepository.save(any(Orden.class))).thenReturn(guardada);

        Orden resultado = ordenService.crearOrden(orden, "token");

        assertEquals(10L, resultado.getId());
        assertEquals(7L, resultado.getUsuarioId());
        verify(restTemplate, times(2)).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Boolean.class));
        verify(ordenRepository).save(any(Orden.class));
    }
}



