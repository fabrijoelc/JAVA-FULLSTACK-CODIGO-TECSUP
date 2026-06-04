package com.codigo.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "matricula")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_id")
    private int matriculaId;

    @ManyToOne
    @JoinColumn(name = "estudiante_dni")
    private EstudianteEntity estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoEntity curso;

    @Column(name = "fechainscripcion")
    private LocalDate fechaInscripcion;
}
