package com.itsqmet.taller1.Controlador;

import java.util.HashMap;
import java.util.Map;

import com.itsqmet.taller1.Entidad.Estudiante;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/Estudiante")
public class EstudianteControlador {

    @PostMapping("/registro")
    @ResponseBody
    public ResponseEntity<?> procesarRegistro(@Valid @ModelAttribute("estudiante") Estudiante estudiante,
                                              BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Lógica para guardar el estudiante
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registro exitoso!");
            response.put("redirectUrl", "/Estudiante/estudiante?id=" + estudiante.getCedula());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error al procesar el registro");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}







