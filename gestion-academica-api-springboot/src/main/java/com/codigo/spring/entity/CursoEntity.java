package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_dni")
    private ProfesorEntity profesor;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private AsignaturaEntity asignatura;
}
