package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Servicio.EstudianteServicio;
import com.itsqmet.taller1.Servicio.ProfesorServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfesorControlador {

    private final ProfesorServicio profesorServicio;

    public ProfesorControlador(ProfesorServicio profesorServicio) {
        this.profesorServicio = profesorServicio;
    }

    // Mostrar la lista de profesores
    @GetMapping("/profesores")
    public String listarProfesores(@RequestParam(required = false) String nombre, Model model) {
        model.addAttribute("profesor", profesorServicio.buscarProfesorPorNombre(nombre));  // Buscar por nombre
        return "Profesor/profesor";  // Ruta de la vista
    }

    // Mostrar formulario para crear un profesor
    @GetMapping("/profesorIndex") // Cambio aquí
    public String mostrarFormulario(Model model) {
        model.addAttribute("profesor", new Profesor()); // Se usa "profesor" para coincidir con el formulario.
        return "index";
    }

    // Guardar profesor
    @PostMapping("/Profesor/registro")
    @ResponseBody
    public ResponseEntity<?> guardarProfesor(@RequestBody Profesor profesor) {
        System.out.println("Datos recibidos: " + profesor); // Depuración

        // Guardar en la base de datos
        profesorServicio.guardarProfesor(profesor);

        // Respuesta JSON
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro exitoso!");
        response.put("redirectUrl", "/profesores");

        return ResponseEntity.ok(response);
    }


    // Eliminar un profesor por nombre
    @GetMapping("/eliminarProfesor/{nombre}")  // Cambié la ruta
    public String eliminarProfesor(@PathVariable String nombre) {
        profesorServicio.eliminarProfesor(nombre);  // Eliminar profesor por nombre
        return "redirect:/profesores";  // Redirigir a la lista
    }
}





