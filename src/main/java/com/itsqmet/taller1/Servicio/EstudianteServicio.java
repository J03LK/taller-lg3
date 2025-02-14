package com.itsqmet.taller1.Servicio;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Usuario;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EstudianteServicio {
    private final EstudianteRepositorio estudianteRepositorio;
    private final UsuarioServicio usuarioServicio;
    private final PasswordEncoder passwordEncoder;

    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio,
                              UsuarioServicio usuarioServicio,
                              PasswordEncoder passwordEncoder) {
        this.estudianteRepositorio = estudianteRepositorio;
        this.usuarioServicio = usuarioServicio;
        this.passwordEncoder = passwordEncoder;
    }

    public void guardar(Estudiante estudiante) {
        // Encriptar la contraseña
        estudiante.setPassword(passwordEncoder.encode(estudiante.getPassword()));

        // Guardar el estudiante
        estudianteRepositorio.save(estudiante);

        // Crear el usuario correspondiente
        Usuario usuario = new Usuario();
        usuario.setUsername(estudiante.getEmail());
        usuario.setPassword(estudiante.getPassword()); // Ya está encriptada
        usuario.setRoles(Set.of("ESTUDIANTE"));
        usuarioServicio.guardarUsuario(usuario);
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepositorio.findAll();
    }

    public Optional<Estudiante> buscarPorEmail(String email) {
        return estudianteRepositorio.findByEmail(email);
    }
}



