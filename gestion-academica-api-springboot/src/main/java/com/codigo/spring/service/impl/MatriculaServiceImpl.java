package com.codigo.spring.service.impl;

import com.codigo.spring.entity.CursoEntity;
import com.codigo.spring.entity.EstudianteEntity;
import com.codigo.spring.entity.MatriculaEntity;
import com.codigo.spring.repository.CursoRepository;
import com.codigo.spring.repository.EstudianteRepository;
import com.codigo.spring.repository.MatriculaRepository;
import com.codigo.spring.request.MatriculaRequest;
import com.codigo.spring.response.MatriculaResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.MatriculaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public MatriculaServiceImpl(
            MatriculaRepository matriculaRepository,
            EstudianteRepository estudianteRepository,
            CursoRepository cursoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public ResponseBase<MatriculaResponse> save(MatriculaRequest request) {
        Optional<EstudianteEntity> estudianteOpt = estudianteRepository.findById(request.getEstudiante());
        Optional<CursoEntity> cursoOpt = cursoRepository.findById(request.getCurso());

        if (estudianteOpt.isEmpty()) {
            return new ResponseBase<>(404, "Estudiante no encontrado", null);
        }

        if (cursoOpt.isEmpty()) {
            return new ResponseBase<>(404, "Curso no encontrado", null);
        }

        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setEstudiante(estudianteOpt.get());
        matricula.setCurso(cursoOpt.get());
        matricula.setFechaInscripcion(LocalDate.now());

        MatriculaEntity saved = matriculaRepository.save(matricula);

        return new ResponseBase<>(200, "Matricula guardada correctamente", toResponse(saved));
    }

    @Override
    public ResponseBase<MatriculaResponse> getById(Integer id) {
        Optional<MatriculaEntity> optional = matriculaRepository.findById(id);

        if (optional.isPresent()) {
            return new ResponseBase<>(200, "Matricula encontrada", toResponse(optional.get()));
        } else {
            return new ResponseBase<>(404, "Matricula no encontrada", null);
        }
    }

    private MatriculaResponse toResponse(MatriculaEntity matricula) {
        EstudianteEntity estudiante = matricula.getEstudiante();
        CursoEntity curso = matricula.getCurso();

        return new MatriculaResponse(
                estudiante.getNombre() + " " + estudiante.getApellido(),
                curso.getNombre(),
                matricula.getFechaInscripcion()
        );
    }
}
