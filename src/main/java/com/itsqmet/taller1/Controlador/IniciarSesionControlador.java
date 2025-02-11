package com.itsqmet.taller1.Controlador;


import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import com.itsqmet.taller1.Repositorio.ProfesorRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class IniciarSesionControlador {

    private final EstudianteRepositorio estudianteRepositorio;
    private final ProfesorRepositorio profesorRepositorio;

    public IniciarSesionControlador(EstudianteRepositorio estudianteRepositorio, ProfesorRepositorio profesorRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
        this.profesorRepositorio = profesorRepositorio;
    }

    @GetMapping("/iniciarSesion")
    public String mostrarPaginaIniciarSesion() {
        return "iniciarSesion"; // Renderiza la vista de inicio de sesión
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // Verificar en la colección de estudiantes
        List<Estudiante> estudiantes = estudianteRepositorio.findByEmail(username);
        if (!estudiantes.isEmpty() && estudiantes.get(0).getPassword().equals(password)) {
            return "redirect:/Estudiante/estudiante";
        }

        // Verificar en la colección de profesores
        List<Profesor> profesores = profesorRepositorio.findByEmail(username);
        if (!profesores.isEmpty() && profesores.get(0).getPassword().equals(password)) {
            return "redirect:/Profesor/profesor";
        }

        // Si no se encontró ninguna coincidencia
        redirectAttributes.addFlashAttribute("error", "Credenciales inválidas. Verifica tu usuario y contraseña.");
        return "redirect:/iniciarSesion";
    }

    @GetMapping("/Profesor/profesor")
    public String mostrarPaginaProfesor(Model model) {
        // Obtener los estudiantes (o cualquier dato necesario)
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();  // Ejemplo para obtener todos los estudiantes

        // Agregar la lista de estudiantes al modelo
        model.addAttribute("estudiantes", estudiantes);

        return "Profesor/profesor";  // La vista que contiene la tabla
    }
    @GetMapping("/Estudiante/estudiante")
    public String mostrarPaginaEstudiante(Model model) {
        return "Estudiante/estudiante";  // La vista que contiene la tabla
    }

}


