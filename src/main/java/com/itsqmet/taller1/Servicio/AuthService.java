package com.itsqmet.taller1.Servicio;

import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import com.itsqmet.taller1.Repositorio.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final EstudianteRepositorio estudianteRepositorio;
    private final ProfesorRepositorio profesorRepositorio;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       EstudianteRepositorio estudianteRepositorio,
                       ProfesorRepositorio profesorRepositorio) {
        this.authenticationManager = authenticationManager;
        this.estudianteRepositorio = estudianteRepositorio;
        this.profesorRepositorio = profesorRepositorio;
    }

    public String autenticarUsuario(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            if (estudianteRepositorio.findByEmail(username).isPresent()) {
                return "redirect:/Estudiante/estudiante";
            } else if (profesorRepositorio.findByEmail(username).isPresent()) {
                return "redirect:/Profesor/profesor";
            }

            return "redirect:/index";

        } catch (AuthenticationException e) {
            return "redirect:/?error=Credenciales inválidas";
        }
    }
}
