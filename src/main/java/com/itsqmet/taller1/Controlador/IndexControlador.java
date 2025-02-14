package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("estudiante", new Estudiante());
        return "index";
    }
}

