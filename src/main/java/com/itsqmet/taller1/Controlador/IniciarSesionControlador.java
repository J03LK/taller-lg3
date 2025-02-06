package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Estudiante;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class IniciarSesionControlador {

    @GetMapping("/iniciarSesion")
    public String mostrarPaginaIniciarSesion() {
        return "iniciarSesion"; // Renderiza la vista de inicio de sesión
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // Aquí deberías validar las credenciales del usuario
        // Por simplicidad, asumimos que las credenciales son válidas

        // Crear un objeto Estudiante (simulado)
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan Pérez"); // Ejemplo de nombre
        estudiante.setEmail("juan@gmail.com"); // Ejemplo de email
        estudiante.setTelefono("123456789"); // Ejemplo de teléfono
        estudiante.setFechaNacimiento(LocalDate.of(1990, 1, 1)); // Ejemplo de fecha de nacimiento
        estudiante.setDireccion("Calle Falsa 123"); // Ejemplo de dirección
        estudiante.setCurso("Java Script"); // Ejemplo de curso
        estudiante.setGenero("Masculino"); // Ejemplo de género

        // Agregar el estudiante al modelo para que esté disponible en la vista
        redirectAttributes.addFlashAttribute("estudiante", estudiante);

        // Redirigir a la página del estudiante
        return "redirect:/estudiante/estudiante";
    }
}