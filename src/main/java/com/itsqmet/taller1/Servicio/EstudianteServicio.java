package com.itsqmet.taller1.Servicio;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteServicio {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    // Buscar estudiantes por nombre
    public List<Estudiante> buscarEstudiantePorNombre(String nombre) {
        return nombre == null ? estudianteRepositorio.findAll() : estudianteRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    // Guardar un estudiante
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    // Eliminar estudiante por nombre
    public boolean eliminarEstudiante(String nombre) {
        List<Estudiante> estudiantes = estudianteRepositorio.findByNombreContainingIgnoreCase(nombre);
        if (!estudiantes.isEmpty()) {
            estudiantes.forEach(estudianteRepositorio::delete);
            return true;
        }
        return false;
    }
}



