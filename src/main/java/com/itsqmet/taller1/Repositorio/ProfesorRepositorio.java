package com.itsqmet.taller1.Repositorio;

import com.itsqmet.taller1.Entidad.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepositorio extends MongoRepository<Profesor, String> {
    List<Profesor> findByNombreContainingIgnoreCase(String nombre);
    Optional<Profesor> findByEmail(String email); // Cambiar a Optional
}