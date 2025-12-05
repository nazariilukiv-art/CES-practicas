package com.example.pizzeriaapp.dao;

import com.example.pizzeriaapp.model.Pedido;
import java.sql.SQLException;
import java.util.List;

public interface PedidoDao {
    void insertarPedido(Pedido pedido) throws SQLException;
    List<Pedido> obtenerPedidos();
    List<Pedido> obtenerPendientes(); // Para el botón de pendientes
    void servirPedido(int idPedido) throws SQLException;
}