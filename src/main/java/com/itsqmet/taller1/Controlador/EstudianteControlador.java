package com.itsqmet.taller1.Controlador;


import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class EstudianteControlador {

    @Autowired
    private EstudianteServicio estudianteServicio;

    // Mostrar la lista de estudiantes
    @GetMapping("/estudiantes")
    public String listarEstudiantes(@RequestParam(required = false) String nombre, Model model) {
        model.addAttribute("estudiantes", estudianteServicio.buscarEstudiantePorNombre(nombre));
        return "Estudiante/estudiante";
    }

    // Mostrar formulario para crear un estudiante
    @GetMapping("/index")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "index";
    }

    // Guardar estudiante
    @PostMapping("/Estudiante/estudiante")
    @ResponseBody
    public ResponseEntity<?> guardarEstudiante(@RequestBody Estudiante estudiante) {
        estudianteServicio.guardarEstudiante(estudiante); // Guarda el estudiante en la base de datos
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro exitoso!");
        response.put("redirectUrl", "/estudiantes");
        return ResponseEntity.ok(response);
    }

    // Eliminar un estudiante por nombre
    @GetMapping("/eliminar/{nombre}")
    public String eliminarEstudiante(@PathVariable String nombre) {
        boolean eliminado = estudianteServicio.eliminarEstudiante(nombre);
        if (!eliminado) {
            return "error";  // Redirigir a una página de error si no se pudo eliminar
        }
        return "redirect:/estudiantes";
    }
}














