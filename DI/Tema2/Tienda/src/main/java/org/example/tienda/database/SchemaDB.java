package org.example.tienda.database;

public interface SchemaDB {
    String TBL_USUARIOS = "usuarios";
    String COL_ID = "id";
    String COL_CORREO = "correo";
    String COL_PASS = "password";

    String TBL_PRODUCTOS = "productos";
    String COL_PROD_NOMBRE = "nombre";
    String COL_PROD_PRECIO = "precio";
    String COL_PROD_STOCK = "stock";
}