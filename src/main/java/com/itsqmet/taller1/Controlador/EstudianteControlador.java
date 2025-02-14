package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Servicio.EstudianteServicio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/estudiantes") // <--  Mapea este controlador a /estudiantes
public class EstudianteControlador {

    private final EstudianteServicio estudianteServicio;

    public EstudianteControlador(EstudianteServicio estudianteServicio) {
        this.estudianteServicio = estudianteServicio;
    }

    @GetMapping // <-- Esto ahora mapea a /estudiantes
    public String index(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "index"; // Si quieres que este metodo retorne la misma vista "index" que el IndexControlador, puedes dejarlo así.
    }

    @GetMapping("/registro") // <-- Esto mapea a /estudiantes/registro
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante";
    }

    @PostMapping("/registro") // <-- Esto mapea a /estudiantes/registro
    public String registrarEstudiante(@ModelAttribute Estudiante estudiante, RedirectAttributes redirectAttributes) {
        try {
            estudianteServicio.guardar(estudiante);
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso. Por favor inicia sesión.");
            return "redirect:/"; // Redirige a la raíz
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar: " + e.getMessage());
            return "redirect:/estudiantes/registro"; // Redirige al formulario de registro
        }
    }

    @PostMapping("/estudiantes")
    public ResponseEntity<String> registrarEstudiantePost(@RequestBody Estudiante estudiante) {
        try {
            estudianteServicio.guardar(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante registrado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar: " + e.getMessage());
        }
    }

    @GetMapping("/Estudiante/estudiante")
    public String paginaEstudiante(Authentication authentication, Model model) {
        String email = authentication.getName();
        estudianteServicio.buscarPorEmail(email).ifPresent(estudiante ->
                model.addAttribute("estudiante", estudiante)
        );
        return "Estudiante/estudiante";
    }
}

