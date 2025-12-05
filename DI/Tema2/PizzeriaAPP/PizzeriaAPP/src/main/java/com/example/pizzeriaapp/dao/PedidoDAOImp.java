package com.example.pizzeriaapp.dao;

import com.example.pizzeriaapp.database.DBConnection;
import com.example.pizzeriaapp.database.SchemeDB;
import com.example.pizzeriaapp.model.Pedido;
import com.example.pizzeriaapp.model.Pizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImp implements PedidoDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public PedidoDAOImp() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void insertarPedido(Pedido pedido) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?)",
                SchemeDB.TAB_PEDIDOS,
                SchemeDB.COL_CLIENTE, SchemeDB.COL_TELEFONO,
                SchemeDB.COL_PIZZA_NOM, SchemeDB.COL_PIZZA_TAM, SchemeDB.COL_PIZZA_PRECIO,
                SchemeDB.COL_SERVIDO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pedido.getNombreCliente());
        preparedStatement.setString(2, pedido.getTelefono());
        preparedStatement.setString(3, pedido.getPizza().getNombre());
        preparedStatement.setString(4, pedido.getPizza().getTamano());
        preparedStatement.setDouble(5, pedido.getPizza().getPrecio());
        preparedStatement.setBoolean(6, pedido.isServido());

        preparedStatement.execute();
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        String query = "SELECT * FROM " + SchemeDB.TAB_PEDIDOS;
        return ejecutarConsulta(query);
    }

    @Override
    public List<Pedido> obtenerPendientes() {
        // Solo los que servido = false (0)
        String query = "SELECT * FROM " + SchemeDB.TAB_PEDIDOS + " WHERE " + SchemeDB.COL_SERVIDO + " = 0";
        return ejecutarConsulta(query);
    }

    @Override
    public void servirPedido(int idPedido) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemeDB.TAB_PEDIDOS, SchemeDB.COL_SERVIDO, SchemeDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBoolean(1, true); // Marcar como servido
        preparedStatement.setInt(2, idPedido);
        preparedStatement.executeUpdate();
    }

    // Metodo auxiliar para no repetir codigo de lectura del ResultSet
    private List<Pedido> ejecutarConsulta(String query) {
        List<Pedido> lista = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Pizza pizza = new Pizza(
                        rs.getString(SchemeDB.COL_PIZZA_NOM),
                        rs.getString(SchemeDB.COL_PIZZA_TAM),
                        rs.getDouble(SchemeDB.COL_PIZZA_PRECIO)
                );
                Pedido pedido = new Pedido(
                        rs.getInt(SchemeDB.COL_ID),
                        rs.getString(SchemeDB.COL_CLIENTE),
                        rs.getString(SchemeDB.COL_TELEFONO),
                        pizza,
                        rs.getBoolean(SchemeDB.COL_SERVIDO)
                );
                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
        return lista;
    }
}