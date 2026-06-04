package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class EstudianteEntity {

    @Id
    private String dni;

    private String nombre;

    private String apellido;

    @Column(unique = true)
    private String email;

    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;
}
