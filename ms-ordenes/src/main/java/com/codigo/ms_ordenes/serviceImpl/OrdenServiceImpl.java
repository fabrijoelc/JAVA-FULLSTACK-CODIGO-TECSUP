package com.codigo.ms_ordenes.serviceImpl;

import com.codigo.ms_ordenes.aggregates.constants.Constants;
import com.codigo.ms_ordenes.entity.Orden;
import com.codigo.ms_ordenes.repository.OrdenRepository;
import com.codigo.ms_ordenes.service.OrdenService;
import com.codigo.ms_ordenes.service.util.AuthValidator;
import com.codigo.ms_ordenes.service.util.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final AuthValidator authValidator;
    private final RestTemplate restTemplate;

    @Value("${ms.productos.url:" + Constants.URL_PRODUCTOS + "}")
    private String productosUrl;

    @Override
    public Orden crearOrden(Orden orden, String token) {
        UsuarioDTO usuario = authValidator.validateToken(token);
        String rol = usuario.getRol();

        if (rol == null || !rol.equalsIgnoreCase(Constants.ROLE_USUARIO)) {
            throw new RuntimeException("Solo los usuarios pueden crear ordenes");
        }

        for (Long idProd : orden.getProductosIds()) {
            validarProducto(idProd, token);
        }

        orden.setUsuarioId(usuario.getId());
        orden.setFecha(LocalDateTime.now());

        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> listarOrdenes(String token) {
        UsuarioDTO usuario = authValidator.validateToken(token);
        String rol = usuario.getRol();

        if (rol == null ||
                (!rol.equalsIgnoreCase(Constants.ROLE_ADMIN) &&
                        !rol.equalsIgnoreCase(Constants.ROLE_SUPERADMIN))) {
            throw new RuntimeException("Solo los administradores pueden ver las ordenes");
        }

        return ordenRepository.findAll();
    }

    private void validarProducto(Long idProd, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    productosUrl + "/" + idProd + "/exists",
                    HttpMethod.GET,
                    entity,
                    Boolean.class
            );

            if (response == null || !response.getStatusCode().is2xxSuccessful() ||
                    !Boolean.TRUE.equals(response.getBody())) {
                throw new RuntimeException("Producto con ID " + idProd + " no valido");
            }
        } catch (Exception e) {
            throw new RuntimeException("Producto con ID " + idProd + " no valido");
        }
    }
}
