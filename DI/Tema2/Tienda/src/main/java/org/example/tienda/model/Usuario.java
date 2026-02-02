package org.example.tienda.model;

public class Usuario {
    private int id;
    private String correo;
    private String password;

    public Usuario(int id, String correo, String password) {
        this.id = id;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public int getId() { return id; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
}