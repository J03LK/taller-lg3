package com.itsqmet.taller1.Servicio;


import com.itsqmet.taller1.Entidad.Profesor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServicio {
    private final List<Profesor> profesores = new ArrayList<>();

    // Agregar un profesor
    public Profesor agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
        return profesor;
    }

    // Obtener todos los profesores
    public List<Profesor> obtenerTodos() {
        return profesores;
    }

    // Buscar un profesor por cédula
    public Profesor obtenerPorCedula(String cedula) {
        return profesores.stream()
                .filter(p -> p.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    // Actualizar un profesor
    public Profesor actualizarProfesor(String cedula, Profesor profesorActualizado) {
        Profesor profesor = obtenerPorCedula(cedula);
        if (profesor != null) {
            profesor.setNombre(profesorActualizado.getNombre());
            profesor.setEmail(profesorActualizado.getEmail());
            profesor.setPassword(profesorActualizado.getPassword());
            profesor.setTelefono(profesorActualizado.getTelefono());
            profesor.setFechaNacimiento(profesorActualizado.getFechaNacimiento());
            profesor.setDireccion(profesorActualizado.getDireccion());
            profesor.setGenero(profesorActualizado.getGenero());
            profesor.setTitulo(profesorActualizado.getTitulo());
            profesor.setAniosExperiencia(profesorActualizado.getAniosExperiencia());
        }
        return profesor;
    }

    // Eliminar un profesor por cédula
    public boolean eliminarProfesor(String cedula) {
        return profesores.removeIf(p -> p.getCedula().equals(cedula));
    }
}
