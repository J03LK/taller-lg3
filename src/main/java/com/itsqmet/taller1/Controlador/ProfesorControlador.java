package com.itsqmet.taller1.Controlador;

import com.itsqmet.taller1.Entidad.Profesor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/profesor")
public class ProfesorControlador {

    @PostMapping("/registro")
    @ResponseBody
    public ResponseEntity<?> procesarRegistro(@Valid @ModelAttribute("profesor") Profesor profesor,
                                              BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            // Lógica para guardar el profesor
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registro exitoso!");
            response.put("redirectUrl", "/profesor/inicio");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error al procesar el registro");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}


