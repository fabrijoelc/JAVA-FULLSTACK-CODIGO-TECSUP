package com.codigo.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "aerolineas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AerolineaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aerolinea")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @OneToMany(mappedBy = "aerolinea")
    List<AvionEntity> aviones;
}
