package com.itsqmet.taller1.Controlador;


import com.itsqmet.taller1.Servicio.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "iniciarSesion";  // Página de inicio de sesión
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam String username,
                                @RequestParam String password) {
        return authService.autenticarUsuario(username, password);
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        return "redirect:/?logout=true";
    }
}
