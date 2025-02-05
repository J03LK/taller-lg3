package com.itsqmet.taller1.Servicio;

import com.itsqmet.taller1.Entidad.Estudiante;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServicio {
    private final List<Estudiante> estudiantes = new ArrayList<>();

    // Agregar un estudiante
    public Estudiante agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        return estudiante;
    }

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }

    // Buscar un estudiante por cédula
    public Estudiante obtenerPorCedula(String cedula) {
        return estudiantes.stream()
                .filter(e -> e.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    // Actualizar un estudiante
    public Estudiante actualizarEstudiante(String cedula, Estudiante estudianteActualizado) {
        Estudiante estudiante = obtenerPorCedula(cedula);
        if (estudiante != null) {
            estudiante.setNombre(estudianteActualizado.getNombre());
            estudiante.setEmail(estudianteActualizado.getEmail());
            estudiante.setTelefono(estudianteActualizado.getTelefono());
            estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
            estudiante.setDireccion(estudianteActualizado.getDireccion());
            estudiante.setGenero(estudianteActualizado.getGenero());
            estudiante.setCurso(estudianteActualizado.getCurso());
        }
        return estudiante;
    }

    // Eliminar un estudiante por cédula
    public boolean eliminarEstudiante(String cedula) {
        return estudiantes.removeIf(e -> e.getCedula().equals(cedula));
    }
}
