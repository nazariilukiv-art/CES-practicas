package com.example.pizzeriaapp.controller;

import com.example.pizzeriaapp.model.Pedido;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetallePedidoController implements Initializable {

    @FXML
    private Label lblId, lblCliente, lblTelefono, lblPizza, lblTamano, lblPrecio, lblEstado;
    @FXML
    private Button btnCerrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCerrar.setOnAction(actionEvent -> {
            ((Stage) btnCerrar.getScene().getWindow()).close();
        });
    }

    public void setPedido(Pedido pedido) {
        lblId.setText("ID: " + pedido.getId());
        lblCliente.setText("Cliente: " + pedido.getNombreCliente());
        lblTelefono.setText("Telefono: " + pedido.getTelefono());
        lblPizza.setText("Pizza: " + pedido.getPizza().getNombre());
        lblTamano.setText("Tamaño: " + pedido.getPizza().getTamano());
        lblPrecio.setText("Precio: " + String.format("%.2f", pedido.getPizza().getPrecio()) + "€");
        lblEstado.setText("Estado: " + (pedido.isServido() ? "Servido" : "Pendiente"));
    }
}