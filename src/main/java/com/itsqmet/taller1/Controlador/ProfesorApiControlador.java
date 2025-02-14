package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Profesor;
import com.itsqmet.taller1.Servicio.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorApiControlador {

    private final ProfesorServicio profesorServicio;

    @Autowired
    public ProfesorApiControlador(ProfesorServicio profesorServicio) {
        this.profesorServicio = profesorServicio;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> guardarProfesor(@RequestBody Profesor profesor) {
        profesorServicio.guardarProfesor(profesor);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro exitoso!");
        response.put("redirectUrl", "/profesores");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProfesor(@PathVariable String id) {
        profesorServicio.eliminarProfesor(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Profesor eliminado"));
    }
}
