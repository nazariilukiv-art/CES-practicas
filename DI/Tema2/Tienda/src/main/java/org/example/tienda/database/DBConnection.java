package org.example.tienda.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/tienda_examen", "root", "1234");
                System.out.println("Conexión exitosa");
            } catch (SQLException e) {
                throw new RuntimeException("Error al conectar BBDD: " + e.getMessage());
            }
        }
        return connection;
    }
}