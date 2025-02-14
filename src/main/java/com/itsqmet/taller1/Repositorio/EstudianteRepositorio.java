package com.itsqmet.taller1.Repositorio;

import com.itsqmet.taller1.Entidad.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepositorio extends MongoRepository<Estudiante, String> {
    Optional<Estudiante> findByEmail(String email); // Cambiar a Optional
}