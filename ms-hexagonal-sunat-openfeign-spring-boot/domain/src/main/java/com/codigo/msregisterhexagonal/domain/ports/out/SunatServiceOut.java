package com.codigo.msregisterhexagonal.domain.ports.out;

import com.codigo.msregisterhexagonal.domain.model.Empresa;

public interface SunatServiceOut {

    Empresa consultarPorRuc(String ruc);
}
