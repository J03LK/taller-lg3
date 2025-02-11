package com.itsqmet.taller1.Entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Document(collection = "profesores") // Indica que se guardará en la colección "profesores"
public class Profesor {

    @Id
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "El nombre debe comenzar con mayúscula y solo contener letras y espacios")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Debe ser un correo electrónico válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
            message = "La contraseña debe tener entre 8 y 20 caracteres, incluir al menos una mayúscula, una minúscula, un número y un carácter especial")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "Debe seleccionar un género")
    private String genero;

    @NotBlank(message = "Debe especificar un título válido")
    @Pattern(regexp = "^(Ingeniero en Software|Ing\\. en Sistemas|Técnico en Software|Carreras afines)$",
            message = "El título debe ser uno de los siguientes: Ingeniero en Software, Ing. en Sistemas, Técnico en Software o carreras afines")
    private String titulo;

    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    @Max(value = 50, message = "Los años de experiencia deben ser razonables (máximo 50)")
    private int aniosExperiencia;

    // Getters y Setters


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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}

