package com.itsqmet.taller1.Controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedireccionController {

    @GetMapping("/redireccionarPorRol")
    public String redireccionarPorRol(Authentication authentication) {
        if (authentication != null) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ESTUDIANTE"))) {
                return "redirect:/Estudiante/estudiante";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_PROFESOR"))) {
                return "redirect:/Profesor/profesor";
            }
        }
        return "redirect:/";
    }
}