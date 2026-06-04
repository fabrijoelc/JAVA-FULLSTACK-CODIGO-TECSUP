package com.codigo.ms_ordenes.controller;

import com.codigo.ms_ordenes.config.SecurityConfiguration;
import com.codigo.ms_ordenes.entity.Orden;
import com.codigo.ms_ordenes.service.OrdenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdenController.class)
@Import(SecurityConfiguration.class) // Si tienes una clase que personaliza tu seguridad
@AutoConfigureMockMvc(addFilters = false) // DESACTIVA los filtros de seguridad para las pruebas
class OrdenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdenService ordenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarOrdenes_deberiaRetornarOk() throws Exception {
        Orden orden = new Orden();
        orden.setId(1L);
        orden.setUsuarioId(1L);
        orden.setProductosIds(Arrays.asList(1L, 2L));
        orden.setFecha(LocalDateTime.now());

        when(ordenService.listarOrdenes(anyString()))
                .thenReturn(Collections.singletonList(orden));

        mockMvc.perform(get("/ordenes")
                        .header("Authorization", "token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].usuarioId").value(1L));
    }

    @Test
    void crearOrden_deberiaRetornarOk() throws Exception {
        Orden orden = new Orden();
        orden.setProductosIds(Arrays.asList(1L, 2L, 3L));
        orden.setFecha(LocalDateTime.now());

        Orden respuesta = new Orden();
        respuesta.setId(10L);
        respuesta.setUsuarioId(2L);
        respuesta.setProductosIds(orden.getProductosIds());
        respuesta.setFecha(orden.getFecha());

        when(ordenService.crearOrden(any(Orden.class), anyString()))
                .thenReturn(respuesta);

        mockMvc.perform(post("/ordenes")
                        .header("Authorization", "token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orden)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.usuarioId").value(2L));
    }
}



