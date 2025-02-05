package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Servicio.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorControlador {

    @Autowired
    private ProfesorServicio profesorServicio;

    // Mostrar la página inicial de profesores
    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "Profesor/profesor";
    }

    // Agregar un nuevo profesor
    @PostMapping("/agregar")
    public Profesor agregarProfesor(@RequestBody Profesor profesor) {
        return profesorServicio.agregarProfesor(profesor);
    }

    // Obtener todos los profesores
    @GetMapping("/listar")
    public List<Profesor> listarProfesores() {
        return profesorServicio.obtenerTodos();
    }

    // Obtener un profesor por cédula
    @GetMapping("/{cedula}")
    public Profesor obtenerProfesor(@PathVariable String cedula) {
        return profesorServicio.obtenerPorCedula(cedula);
    }

    // Actualizar un profesor
    @PutMapping("/actualizar/{cedula}")
    public Profesor actualizarProfesor(@PathVariable String cedula, @RequestBody Profesor profesorActualizado) {
        return profesorServicio.actualizarProfesor(cedula, profesorActualizado);
    }

    // Eliminar un profesor
    @DeleteMapping("/eliminar/{cedula}")
    public String eliminarProfesor(@PathVariable String cedula) {
        boolean eliminado = profesorServicio.eliminarProfesor(cedula);
        return eliminado ? "Profesor eliminado correctamente" : "No se encontró el profesor";
    }
}


