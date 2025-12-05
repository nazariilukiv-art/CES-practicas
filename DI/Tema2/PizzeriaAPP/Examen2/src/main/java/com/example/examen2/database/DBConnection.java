package com.example.examen2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    private static Connection connection;

    public static Connection getConnection(){
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        String user = "root";
        String pass = "1234";

        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s" /*ShemaDB.URL, ShemaDB.PORT, ShemaDB.DB_NAME*/), user, pass);
            System.out.println("Conexion a la BBDD");
        } catch (SQLException e) {
            System.out.println("Error con la base de datos");
            System.out.println(e.getMessage());
        }

    }
}
