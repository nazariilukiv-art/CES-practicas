package com.example.pizzeriaapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int id;
    private String nombreCliente;
    private String telefono;
    private Pizza pizza;
    private boolean servido;


    public Pedido(String nombreCliente, String telefono, Pizza pizza, boolean servido) {
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.pizza = pizza;
        this.servido = servido;
    }

    @Override
    public String toString() {

        return id + " - " + nombreCliente + " - " + pizza.getNombre();
    }
}