package com.example.pizzeriaapp.database;

public interface SchemeDB {
    String URL = "127.0.0.1";
    String PORT = "3307";
    String DB_NAME = "pizzeria";

    String TAB_PEDIDOS = "pedidos";

    String COL_ID = "id";
    String COL_CLIENTE = "nombre";
    String COL_TELEFONO = "telefono";
    String COL_PIZZA_NOM = "pizza";
    String COL_PIZZA_TAM = "tamano";
    String COL_PIZZA_PRECIO = "precio";
    String COL_SERVIDO = "servido";
}