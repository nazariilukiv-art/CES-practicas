package org.example.tienda.dao;

import org.example.tienda.database.DBConnection;
import org.example.tienda.database.SchemaDB;
import org.example.tienda.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        this.connection = DBConnection.getConnection();
    }

    // Busca usuario para login
    public Usuario login(String correo, String password) {
        String sql = "SELECT * FROM " + SchemaDB.TBL_USUARIOS + " WHERE " + SchemaDB.COL_CORREO + " = ? AND " + SchemaDB.COL_PASS + " = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt(SchemaDB.COL_ID), rs.getString(SchemaDB.COL_CORREO), rs.getString(SchemaDB.COL_PASS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Comprueba si el correo ya existe
    public boolean existeCorreo(String correo) {
        String sql = "SELECT * FROM " + SchemaDB.TBL_USUARIOS + " WHERE " + SchemaDB.COL_CORREO + " = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Registra nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO " + SchemaDB.TBL_USUARIOS + " (" + SchemaDB.COL_CORREO + ", " + SchemaDB.COL_PASS + ") VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}