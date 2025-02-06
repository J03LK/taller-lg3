package com.itsqmet.taller1.Entidad;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "estudiantes")
public class Estudiante {
    @Id
    @Pattern(regexp = "^\\d{10}$", message = "La cédula debe tener exactamente 10 dígitos")
    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;

    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El nombre debe comenzar con mayúscula y solo contener letras y espacios")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Debe ser un correo electrónico válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "La contraseña debe tener entre 8 y 20 caracteres, incluir al menos una letra mayúscula, una minúscula, un número y un carácter especial")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "Debe seleccionar un curso")
    private String curso;

    @NotBlank(message = "Debe seleccionar un género")
    private String genero;

    // Getters y Setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
