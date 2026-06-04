package com.codigo.spring.service.impl;

import com.codigo.spring.entity.AsignaturaEntity;
import com.codigo.spring.entity.CursoEntity;
import com.codigo.spring.entity.ProfesorEntity;
import com.codigo.spring.repository.AsignaturaRepository;
import com.codigo.spring.repository.CursoRepository;
import com.codigo.spring.repository.ProfesorRepository;
import com.codigo.spring.request.CursoRequest;
import com.codigo.spring.request.CursoUpdateRequest;
import com.codigo.spring.response.CursoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.CursoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;
    private final AsignaturaRepository asignaturaRepository;

    public CursoServiceImpl(
            CursoRepository cursoRepository,
            ProfesorRepository profesorRepository,
            AsignaturaRepository asignaturaRepository) {
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public ResponseBase<CursoResponse> save(CursoRequest request) {
        Optional<ProfesorEntity> profesorOpt = profesorRepository.findById(request.getProfesor());
        Optional<AsignaturaEntity> asignaturaOpt = asignaturaRepository.findById(request.getAsignatura());

        if (profesorOpt.isEmpty()) {
            return new ResponseBase<>(404, "Profesor no encontrado", null);
        }

        if (asignaturaOpt.isEmpty()) {
            return new ResponseBase<>(404, "Asignatura no encontrada", null);
        }

        CursoEntity curso = new CursoEntity();
        curso.setNombre(request.getNombre());
        curso.setDescripcion(request.getDescripcion());
        curso.setProfesor(profesorOpt.get());
        curso.setAsignatura(asignaturaOpt.get());

        CursoEntity saved = cursoRepository.save(curso);

        return new ResponseBase<>(200, "Curso guardado correctamente", toResponse(saved));
    }

    @Override
    public ResponseBase<CursoResponse> getById(Integer id) {
        Optional<CursoEntity> optional = cursoRepository.findById(id);

        if (optional.isPresent()) {
            return new ResponseBase<>(200, "Curso encontrado", toResponse(optional.get()));
        } else {
            return new ResponseBase<>(404, "Curso no encontrado", null);
        }
    }

    @Override
    public ResponseBase<CursoResponse> update(Integer id, CursoUpdateRequest request) {
        Optional<CursoEntity> cursoOpt = cursoRepository.findById(id);
        Optional<ProfesorEntity> profesorOpt = profesorRepository.findById(request.getProfesor());
        Optional<AsignaturaEntity> asignaturaOpt = asignaturaRepository.findById(request.getAsignatura());

        if (cursoOpt.isEmpty()) {
            return new ResponseBase<>(404, "Curso no encontrado", null);
        }

        if (profesorOpt.isEmpty()) {
            return new ResponseBase<>(404, "Profesor no encontrado", null);
        }

        if (asignaturaOpt.isEmpty()) {
            return new ResponseBase<>(404, "Asignatura no encontrada", null);
        }

        CursoEntity curso = cursoOpt.get();
        curso.setProfesor(profesorOpt.get());
        curso.setAsignatura(asignaturaOpt.get());

        CursoEntity updated = cursoRepository.save(curso);

        return new ResponseBase<>(200, "Curso actualizado correctamente", toResponse(updated));
    }

    private CursoResponse toResponse(CursoEntity curso) {
        ProfesorEntity profesor = curso.getProfesor();
        AsignaturaEntity asignatura = curso.getAsignatura();

        return new CursoResponse(
                curso.getNombre(),
                curso.getDescripcion(),
                profesor.getNombre().charAt(0) + ". " + profesor.getApellido(),
                asignatura.getNombre()
        );
    }
}
