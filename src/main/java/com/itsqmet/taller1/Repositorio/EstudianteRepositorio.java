package com.itsqmet.taller1.Repositorio;

import com.itsqmet.taller1.Entidad.Estudiante;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EstudianteRepositorio {
    @Repository
    public interface AutorRepositorio extends MongoRepository<Estudiante, String> {
        public abstract List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    }

}
