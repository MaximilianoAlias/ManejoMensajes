package com.example.ManejoPlantillas.Domain;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/*cuando usamos la etiqueta de @Data de "lombok" esta nos crea automaticamente
 * pero de manera no visible, los getters, setters, el constructor, los metodos
 * equals, hascode, canequals y toString*/
@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //Indica cual es el campo de la llave primaria en nuestra base de Datos
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GeneratedValue indica como generar el valor de esta llave primaria
    private Long idPersona;

    /*La diferencia entre NotNull y NotEmpty radica en que la segunda osea Empty
    * trabaja especialmente con cadenas y especifica que las cadenas no pueden estar vacias
    * a diferncia de Null que simplemente dice que el valor no puede ser nulo.*/
    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String telefono;

}
