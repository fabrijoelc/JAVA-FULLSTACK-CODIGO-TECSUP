package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "profesor")
public class ProfesorEntity {

    @Id
    private String dni;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;
}
