package com.itsqmet.taller1.Servicio;

import com.itsqmet.taller1.Entidad.Estudiante;
import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Entidad.Usuario;
import com.itsqmet.taller1.Repositorio.EstudianteRepositorio;
import com.itsqmet.taller1.Repositorio.ProfesorRepositorio;
import com.itsqmet.taller1.Repositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServicio implements UserDetailsService {
    private final UsuarioRepositorio usuarioRepositorio;
    private final EstudianteRepositorio estudianteRepositorio;
    private final ProfesorRepositorio profesorRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio,
                           EstudianteRepositorio estudianteRepositorio,
                           ProfesorRepositorio profesorRepositorio,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.estudianteRepositorio = estudianteRepositorio;
        this.profesorRepositorio = profesorRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
        return usuario;
    }

    public void registrarEstudiante(Estudiante estudiante) {
        // Crear usuario con rol ESTUDIANTE
        Usuario usuario = new Usuario();
        usuario.setUsername(estudiante.getEmail());
        usuario.setPassword(passwordEncoder.encode(estudiante.getPassword()));
        usuario.setRoles(Set.of("ESTUDIANTE"));
        usuarioRepositorio.save(usuario);

        // Guardar estudiante
        estudiante.setPassword(passwordEncoder.encode(estudiante.getPassword()));
        estudianteRepositorio.save(estudiante);
    }

    public void registrarProfesor(Profesor profesor) {
        // Crear usuario con rol PROFESOR
        Usuario usuario = new Usuario();
        usuario.setUsername(profesor.getEmail());
        usuario.setPassword(passwordEncoder.encode(profesor.getPassword()));
        usuario.setRoles(Set.of("PROFESOR"));
        usuarioRepositorio.save(usuario);

        // Guardar profesor
        profesor.setPassword(passwordEncoder.encode(profesor.getPassword()));
        profesorRepositorio.save(profesor);
    }
    public void guardarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }
}
