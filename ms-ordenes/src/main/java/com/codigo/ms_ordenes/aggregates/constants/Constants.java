package com.codigo.ms_ordenes.aggregates.constants;

public class Constants {

    // Endpoints que podrían estar libres si quieres habilitar algo
    public static final String[] PUBLIC_ENDPOINTS = {
            "/actuator/**"
    };

    // URL del ms-auth para validar el token
    public static final String URL_VALIDATE_TOKEN = "http://localhost:8080/auth/validate";
    // Si usas Eureka:
    // public static final String URL_VALIDATE_TOKEN = "http://ms-auth/auth/validate";

    // Roles definidos
    public static final String ROLE_USUARIO = "USUARIO";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_SUPERADMIN = "SUPERADMIN";

    // URL para validar productos desde ms-productos
    public static final String URL_PRODUCTOS = "http://localhost:8081/productos";
    // Si usas Eureka:
    // public static final String URL_PRODUCTOS = "http://ms-productos/productos";
}
