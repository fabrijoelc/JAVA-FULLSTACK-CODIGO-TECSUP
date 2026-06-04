package com.codigo.ms_ordenes.controller;

import com.codigo.ms_ordenes.entity.Orden;
import com.codigo.ms_ordenes.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(
            @RequestBody Orden orden,
            @RequestHeader("Authorization") String token
    ) {
        Orden nuevaOrden = ordenService.crearOrden(orden, token);
        return ResponseEntity.ok(nuevaOrden);
    }

    @GetMapping
    public ResponseEntity<List<Orden>> listarOrdenes(
            @RequestHeader("Authorization") String token
    ) {
        List<Orden> ordenes = ordenService.listarOrdenes(token);
        return ResponseEntity.ok(ordenes);
    }
}


