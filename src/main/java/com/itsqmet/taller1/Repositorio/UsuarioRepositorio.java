package com.itsqmet.taller1.Repositorio;

import com.itsqmet.taller1.Entidad.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String username);
}
