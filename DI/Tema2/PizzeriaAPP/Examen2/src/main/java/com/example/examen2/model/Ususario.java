package com.example.examen2.model;

public class Ususario {
   private String nombre;
   private String correo;
   private String pass;

    public Ususario(String nombre, String correo, String pass) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Ususario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
