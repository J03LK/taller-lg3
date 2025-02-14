package com.itsqmet.taller1.Controlador;


import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Servicio.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/profesores")
public class ProfesorVistaControlador {

    private final ProfesorServicio profesorServicio;

    @Autowired
    public ProfesorVistaControlador(ProfesorServicio profesorServicio) {
        this.profesorServicio = profesorServicio;
    }

    @GetMapping
    public String listarProfesores(@RequestParam(required = false) String nombre, Model model) {
        model.addAttribute("profesor", profesorServicio.buscarProfesorPorNombre(nombre));
        return "/profesor";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "/registro"; // Asegúrate de que esta vista exista
    }
}