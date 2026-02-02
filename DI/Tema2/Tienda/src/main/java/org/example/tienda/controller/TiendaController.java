package org.example.tienda.controller;

import org.example.tienda.dao.ProductoDAO;
import org.example.tienda.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TiendaController {

    @FXML
    private ListView<Producto> listaProductos;
    @FXML
    private Label lblTotal;

    private ProductoDAO productoDAO;
    private ObservableList<Producto> carrito;

    public void initialize() {
        productoDAO = new ProductoDAO();
        carrito = FXCollections.observableArrayList();
        cargarProductos();
        actualizarTotal();
    }

    private void cargarProductos() {
        ObservableList<Producto> productos = productoDAO.obtenerTodosProductos();
        listaProductos.setItems(productos);
    }

    @FXML
    public void onComprar() {
        Producto seleccionado = listaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Selección", "Selecciona un producto de la lista");
            return;
        }

        // Evitar duplicados si lo pide el enunciado (opcional, pero buena práctica)
        if (!carrito.contains(seleccionado)) {
            carrito.add(seleccionado);
            actualizarTotal();
            mostrarAlerta("Carrito", "Añadido: " + seleccionado.getNombre());
        } else {
            mostrarAlerta("Información", "Este producto ya está en tu carrito");
        }
    }

    @FXML
    public void onVaciar() {
        carrito.clear();
        actualizarTotal();
        mostrarAlerta("Carrito", "El carrito se ha vaciado");
    }

    @FXML
    public void onVerCarrito() {
        if (carrito.isEmpty()) {
            mostrarAlerta("Carrito", "El carrito está vacío");
            return;
        }

        StringBuilder texto = new StringBuilder("Productos en tu compra:\n");
        double totalPrecio = 0;
        for (Producto p : carrito) {
            texto.append("- ").append(p.getNombre()).append(" : ").append(p.getPrecio()).append("€\n");
            totalPrecio += p.getPrecio();
        }
        texto.append("\nTotal a pagar: ").append(totalPrecio).append("€");

        mostrarAlerta("Resumen de Compra", texto.toString());
    }

    private void actualizarTotal() {
        lblTotal.setText("Productos en carrito: " + carrito.size());
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}