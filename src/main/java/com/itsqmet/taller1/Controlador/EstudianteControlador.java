package com.itsqmet.taller1.Controlador;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itsqmet.taller1.Entidad.Estudiante;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.validation.BindingResult;

@Controller
public class EstudianteControlador {

    private List<Estudiante> estudiantes = new ArrayList<>();

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "index";
    }

    @PostMapping("/Estudiante/registro")
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
            estudiantes.add(estudiante);
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

    @GetMapping("/Estudiante/estudiante")
    public String mostrarEstudiante(@RequestParam(required = false) String id, Model model) {
        // Si no hay ID, mostrar el último estudiante registrado
        Estudiante estudiante;
        if (id != null && !estudiantes.isEmpty()) {
            estudiante = estudiantes.stream()
                    .filter(e -> e.getCedula().equals(id))
                    .findFirst()
                    .orElse(estudiantes.get(estudiantes.size() - 1));
        } else if (!estudiantes.isEmpty()) {
            estudiante = estudiantes.get(estudiantes.size() - 1);
        } else {
            // Si no hay estudiantes, redirigir al inicio
            return "redirect:/";
        }

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("mensaje", "¡Bienvenido " + estudiante.getNombre() + "!");
        return "Estudiante/estudiante";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensajeCierre", "Tu sesión se ha cerrado correctamente");
        return "redirect:/";
    }
}







