package com.codigo.ms_ordenes.service;

import com.codigo.ms_ordenes.entity.Orden;
import java.util.List;

public interface OrdenService {
    Orden crearOrden(Orden orden, String token);
    List<Orden> listarOrdenes(String token);
}
