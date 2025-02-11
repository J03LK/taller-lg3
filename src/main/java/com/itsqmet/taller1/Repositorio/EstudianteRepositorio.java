package com.itsqmet.taller1.Repositorio;

import com.itsqmet.taller1.Entidad.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteRepositorio extends MongoRepository<Estudiante, String> {
    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    List<Estudiante> findByEmail(String email);// Método para buscar por nombre
}
