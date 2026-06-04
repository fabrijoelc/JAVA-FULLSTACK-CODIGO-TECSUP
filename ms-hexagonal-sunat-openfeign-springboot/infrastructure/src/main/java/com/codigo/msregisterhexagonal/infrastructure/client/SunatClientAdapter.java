package com.codigo.msregisterhexagonal.infrastructure.client;

import com.codigo.msregisterhexagonal.domain.model.Empresa;
import com.codigo.msregisterhexagonal.domain.ports.out.SunatServiceOut;
import com.codigo.msregisterhexagonal.infrastructure.exception.ConsultaSunatException;
import org.springframework.stereotype.Component;

@Component
public class SunatClientAdapter implements SunatServiceOut {

    private final SunatFeignClient sunatFeignClient;

    public SunatClientAdapter(SunatFeignClient sunatFeignClient) {
        this.sunatFeignClient = sunatFeignClient;
    }

    @Override
    public Empresa consultarPorRuc(String ruc) {
        SunatResponse sunatResponse = sunatFeignClient.consultarPorRuc(ruc);
        return toEmpresa(sunatResponse);
    }

    private Empresa toEmpresa(SunatResponse sunatResponse) {
        if (sunatResponse == null) {
            throw new ConsultaSunatException("La respuesta de SUNAT esta vacia");
        }

        return Empresa.builder()
                .razonSocial(sunatResponse.getRazonSocial())
                .numeroDocumento(sunatResponse.getNumeroDocumento())
                .estado(sunatResponse.getEstado())
                .condicion(sunatResponse.getCondicion())
                .direccion(sunatResponse.getDireccion())
                .departamento(sunatResponse.getDepartamento())
                .provincia(sunatResponse.getProvincia())
                .distrito(sunatResponse.getDistrito())
                .actividadEconomica(sunatResponse.getActividadEconomica())
                .numeroTrabajadores(sunatResponse.getNumeroTrabajadores())
                .build();
    }

}
