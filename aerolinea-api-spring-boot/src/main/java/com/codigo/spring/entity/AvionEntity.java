package com.codigo.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aviones")
@Getter
@Setter
public class AvionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private Integer id;

    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private int capacidad;

    @Min(value = 1, message = "El peso debe ser mayor a 0")
    private int peso;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_aerolinea_fk")
    private AerolineaEntity aerolinea;
}
