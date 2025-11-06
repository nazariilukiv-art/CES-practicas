package com.example.folmurario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    // --- ¡AÑADIDO! ---
    private Integer id;
    // ------------------

    private String nombre, correo, localizacion, genero;
    private int edad;
    private boolean disponibilidad;

    // --- ¡AÑADIDO! ---
    // Creamos un constructor nuevo que NO pide el ID,
    // ya que lo generará la BBDD.
    public Usuario(String nombre, String correo, String localizacion, String genero, int edad, boolean disponibilidad) {
        this.nombre = nombre;
        this.correo = correo;
        this.localizacion = localizacion;
        this.genero = genero;
        this.edad = edad;
        this.disponibilidad = disponibilidad;
        // this.id se queda como null
    }
}