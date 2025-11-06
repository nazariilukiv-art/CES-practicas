package com.example.folmurario.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    // --- Configuración de la Conexión ---

    // El Host es 'localhost' (127.0.0.1)
    // El Puerto es '3307' (el que mapeaste en Docker)
    // La Base de Datos es 'mi_app_javafx' (la que creamos en Workbench)
    private static final String DB_URL = "jdbc:mysql://localhost:3307/mi_app_javafx";

    // El usuario ('root')
    private static final String USER = "root";

    // ¡LA CONTRASEÑA QUE PUSISTE EN DOCKER!
    private static final String PASSWORD = "1234";
    // ------------------------------------

    /**
     * Intenta establecer una conexión con la base de datos.
     * @return un objeto Connection si tiene éxito, o null si falla.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 1. Cargar el driver (aunque en JDBC 4.0+ es automático)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Intentar la conexión
            System.out.println("Conectando a la base de datos en Docker (mi_app_javafx)...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // 3. Si llegamos aquí, ¡éxito!
            System.out.println("¡CONEXIÓN EXITOSA!");

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL (JDBC) no encontrado.");
            System.err.println("Asegúrate de haber corregido la dependencia en pom.xml y recargado Maven.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            System.err.println("Verifica:");
            System.err.println("1. Que el contenedor Docker 'mi-mysql-db' esté corriendo (docker ps).");
            System.err.println("2. Que la URL, USER y PASSWORD sean correctos en esta clase.");
            System.err.println("3. Que la base de datos 'mi_app_javafx' exista.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Un método main() solo para probar esta clase de forma aislada.
     */
    public static void main(String[] args) {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                conn.close();
                System.out.println("Prueba finalizada. Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        } else {
            System.err.println("La conexión falló. Revisa los mensajes de error de arriba.");
        }
    }
}