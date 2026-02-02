package org.example.tienda.dao;

import org.example.tienda.database.DBConnection;
import org.example.tienda.database.SchemaDB;
import org.example.tienda.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO() {
        this.connection = DBConnection.getConnection();
    }

    public ObservableList<Producto> obtenerTodosProductos() {
        ObservableList<Producto> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM " + SchemaDB.TBL_PRODUCTOS;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt(SchemaDB.COL_ID),
                        rs.getString(SchemaDB.COL_PROD_NOMBRE),
                        rs.getDouble(SchemaDB.COL_PROD_PRECIO),
                        rs.getInt(SchemaDB.COL_PROD_STOCK)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}