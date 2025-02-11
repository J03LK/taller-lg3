package com.itsqmet.taller1.Servicio;


import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import com.itsqmet.taller1.Repositorio.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServicio {
    private final List<Profesor> profesores = new ArrayList<>();

    @Autowired
    private ProfesorRepositorio ProfesorRepositorio;

    // Mostrar todos los estudiantes
    public List<Profesor> mostrarProfesor() {
        return ProfesorRepositorio.findAll();
    }

    // Buscar estudiantes por nombre
    public List<Profesor> buscarProfesorPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return ProfesorRepositorio.findAll();
        } else {
            return ProfesorRepositorio.findByNombreContainingIgnoreCase(nombre.trim());  // Busca por nombre
        }
    }

    // Guardar un estudiante
    public void guardarProfesor(Profesor profesor) {
        ProfesorRepositorio.save(profesor);
    }

    // Eliminar un estudiante
    public void eliminarProfesor(String nombre) {
        List<Profesor> profesor = ProfesorRepositorio.findByNombreContainingIgnoreCase(nombre.trim());
        if (!profesor.isEmpty()) {
           ProfesorRepositorio.delete(profesor.get(0));  // Eliminar el primer estudiante encontrado por nombre
        }
    }
}
