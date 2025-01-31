package com.itsqmet.taller1.Controlador;


import com.itsqmet.taller1.Entidad.Estudiante;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EstudianteControlador {

    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        // Solo agregar el estudiante si no existe en el modelo
        if (!model.containsAttribute("estudiante")) {
            model.addAttribute("estudiante", new Estudiante());
        }
        return "Estudiante/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@Valid Estudiante estudiante,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        // Validar el formulario
        if (result.hasErrors()) {
            // Agregar el estudiante al modelo para mantener los datos ingresados
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.estudiante", result);
            redirectAttributes.addFlashAttribute("estudiante", estudiante);
            return "Estudiante/registro";
        }

        try {
            // Aquí podrías agregar lógica para guardar el estudiante en una base de datos

            // Agregar atributos para la página de éxito
            redirectAttributes.addFlashAttribute("estudiante", estudiante);
            redirectAttributes.addFlashAttribute("mensaje", "¡Registro completado exitosamente!");
            return "redirect:/estudiante";
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir
            redirectAttributes.addFlashAttribute("error", "Error al procesar el registro. Por favor, intente nuevamente.");
            return "redirect:/registro";
        }
    }

    @GetMapping("/estudiante")
    public String mostrarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante, Model model) {
        // Verificar si hay un estudiante
        if (estudiante == null || estudiante.getNombre() == null) {
            // Si no hay estudiante, redirigir a la página de inicio de sesión
            return "redirect:/iniciarSesion";
        }

        // Asegurar que el estudiante esté disponible en la vista
        model.addAttribute("estudiante", estudiante);

        // Si no hay mensaje de éxito, agregar uno por defecto
        if (!model.containsAttribute("mensaje")) {
            model.addAttribute("mensaje", "Perfil de estudiante");
        }

        return "Estudiante/estudiante";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensajeCierre", "Tu sesión se ha cerrado correctamente");
        return "redirect:/";
    }
}

