package com.example.examen2.model;

public class Producto {

    private String nombre;
    private String categoria;
    private String precio;
    private String descripcion;

    public Producto(String nombre, String categoria, String precio, String descripcion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio='" + precio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
