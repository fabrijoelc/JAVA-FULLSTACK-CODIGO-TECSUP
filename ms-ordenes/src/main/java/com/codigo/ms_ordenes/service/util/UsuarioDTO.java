package com.codigo.ms_ordenes.service.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    @Getter
    private Long id;
    private List<String> roles;
    private String email;

    public String getRol() {
        return (roles != null && !roles.isEmpty()) ? roles.get(0) : null;
    }

}



