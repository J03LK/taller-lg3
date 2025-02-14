package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import com.itsqmet.taller1.Repositorio.ProfesorRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
public class IniciarSesionControlador {

    private final EstudianteRepositorio estudianteRepositorio;
    private final ProfesorRepositorio profesorRepositorio;
    private final AuthenticationManager authenticationManager;

    public IniciarSesionControlador(
            EstudianteRepositorio estudianteRepositorio,
            ProfesorRepositorio profesorRepositorio,
            AuthenticationManager authenticationManager) {
        this.estudianteRepositorio = estudianteRepositorio;
        this.profesorRepositorio = profesorRepositorio;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/iniciarSesion")
    public String mostrarPaginaIniciarSesion() {
        return "iniciarSesion";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam String username,
                                @RequestParam String password,
                                HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication =
                    authenticationManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Optional<Estudiante> estudiante = estudianteRepositorio.findByEmail(username);
            Optional<Profesor> profesor = profesorRepositorio.findByEmail(username);

            if (estudiante.isPresent()) {
                return "redirect:/Estudiante/estudiante";
            } else if (profesor.isPresent()) {
                return "redirect:/Profesor/profesor";
            }

            return "redirect:/index";

        } catch (AuthenticationException e) {
            try {
                return "redirect:/?error=" + URLEncoder.encode("Credenciales inválidas", StandardCharsets.UTF_8);
            } catch (Exception ex) {
                return "redirect:/?error=error_autenticacion";
            }
        }
    }

    @GetMapping("/Profesor/profesor")
    public String mostrarPaginaProfesor(Model model) {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        model.addAttribute("estudiantes", estudiantes);
        return "Profesor/profesor";
    }

    @GetMapping("/Estudiante/inicio") // Ruta modificada
    public String mostrarPaginaEstudiante(Model model) {
        return "Estudiante/estudiante";
    }
}

