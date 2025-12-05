package com.example.pizzeriaapp.controller;

import com.example.pizzeriaapp.HelloApplication;
import com.example.pizzeriaapp.dao.PedidoDAOImp;
import com.example.pizzeriaapp.model.Pedido;
import com.example.pizzeriaapp.model.Pizza;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PizzeriaController implements Initializable {

    @FXML
    private Button btnRealizarPedido, btnServir, btnPendientes, btnDetalle;
    @FXML
    private TextField txtNombre, txtTelefono;
    @FXML
    private ComboBox<String> comboPizzas;
    @FXML
    private ListView<Pedido> listaPedidos;
    @FXML
    private ToggleGroup grupoTamano;
    @FXML
    private RadioButton radioFamiliar, radioMediana, radioPequena;
    @FXML
    private Label lblInfoPedido;
    @FXML
    private Label lblPrecioActual;

    private ObservableList<Pedido> observablePedidos;
    private ObservableList<String> nombresPizzas;
    private PedidoDAOImp pedidoDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        initGUI();
        acciones();
    }

    private void instancias() {
        pedidoDAO = new PedidoDAOImp();
        observablePedidos = FXCollections.observableArrayList(pedidoDAO.obtenerPedidos());
        nombresPizzas = FXCollections.observableArrayList();
        nombresPizzas.addAll("Barbacoa", "Hawaiana", "Jamón y queso", "Cuatro quesos");
    }

    private void initGUI() {
        listaPedidos.setItems(observablePedidos);
        comboPizzas.setItems(nombresPizzas);
        grupoTamano.selectToggle(null);
    }

    private void acciones() {
        btnRealizarPedido.setOnAction(new ManejoActions());
        btnServir.setOnAction(new ManejoActions());
        btnPendientes.setOnAction(new ManejoActions());
        btnDetalle.setOnAction(new ManejoActions());

        // Listener para la lista: Mostrar info en pantalla
        listaPedidos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pedido>() {
            @Override
            public void changed(ObservableValue<? extends Pedido> observable, Pedido oldValue, Pedido newValue) {
                if (newValue != null) {
                    String info = "PEDIDO SELECCIONADO:\n" +
                            "Cliente: " + newValue.getNombreCliente() + "\n" +
                            "Pizza: " + newValue.getPizza().getNombre() + " - " + newValue.getPizza().getTamano() + "\n" +
                            "Precio: " + String.format("%.2f", newValue.getPizza().getPrecio()) + "€" + "\n" +
                            "Estado: " + (newValue.isServido() ? "Servido" : "Pendiente");
                    lblInfoPedido.setText(info);
                } else {
                    lblInfoPedido.setText("");
                }
            }
        });

        // Listener para el ComboBox (Tipo de pizza)
        comboPizzas.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                actualizarPrecioEnLabel();
            }
        });

        // Listener para el ToggleGroup (Tamaño de pizza)
        grupoTamano.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                actualizarPrecioEnLabel();
            }
        });
    }

    private void actualizarPrecioEnLabel() {
        String nombrePizza = comboPizzas.getValue();
        RadioButton radioSeleccionado = (RadioButton) grupoTamano.getSelectedToggle();

        if (nombrePizza != null && radioSeleccionado != null) {
            String tamano = radioSeleccionado.getText();
            double precio = calcularPrecio(nombrePizza, tamano);
            lblPrecioActual.setText("Precio: " + String.format("%.2f", precio) + "€");
        } else {
            lblPrecioActual.setText("Precio: 0.00€");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        comboPizzas.getSelectionModel().clearSelection();
        grupoTamano.selectToggle(null);
        lblPrecioActual.setText("Precio: 0.00€");
    }

    private double calcularPrecio(String tipo, String tamano) {
        double precio = 0;
        switch (tipo) {
            case "Barbacoa":
                if (tamano.equals("Pequeña")) precio = 7;
                else if (tamano.equals("Mediana")) precio = 12;
                else if (tamano.equals("Familiar")) precio = 15;
                break;
            case "Hawaiana":
                if (tamano.equals("Pequeña")) precio = 5;
                else if (tamano.equals("Mediana")) precio = 10;
                else if (tamano.equals("Familiar")) precio = 13;
                break;
            case "Jamón y queso":
                if (tamano.equals("Pequeña")) precio = 4;
                else if (tamano.equals("Mediana")) precio = 8;
                else if (tamano.equals("Familiar")) precio = 10;
                break;
            case "Cuatro quesos":
                if (tamano.equals("Pequeña")) precio = 8;
                else if (tamano.equals("Mediana")) precio = 13;
                else if (tamano.equals("Familiar")) precio = 17;
                break;
        }
        return precio;
    }

    class ManejoActions implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            if (event.getSource() == btnRealizarPedido) {
                if (!txtNombre.getText().isEmpty() &&
                        !txtTelefono.getText().isEmpty() &&
                        comboPizzas.getValue() != null &&
                        grupoTamano.getSelectedToggle() != null) {

                    String nombre = txtNombre.getText();
                    String telefono = txtTelefono.getText();
                    String nombrePizza = comboPizzas.getValue();
                    String tamano = ((RadioButton) grupoTamano.getSelectedToggle()).getText();

                    double precio = calcularPrecio(nombrePizza, tamano);
                    Pizza pizza = new Pizza(nombrePizza, tamano, precio);

                    Pedido pedido = new Pedido(nombre, telefono, pizza, false);

                    try {
                        pedidoDAO.insertarPedido(pedido);
                        observablePedidos.setAll(pedidoDAO.obtenerPedidos());

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Éxito");
                        alert.setContentText("Pedido realizado correctamente");
                        alert.show();

                        limpiarCampos();

                    } catch (SQLException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("No se pudo insertar el pedido en la base de datos.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Campos incompletos");
                    alert.setContentText("Por favor rellena todos los campos");
                    alert.show();
                }

            } else if (event.getSource() == btnServir) {
                Pedido seleccionado = listaPedidos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    try {
                        pedidoDAO.servirPedido(seleccionado.getId());
                        observablePedidos.setAll(pedidoDAO.obtenerPedidos());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Éxito");
                        alert.setContentText("Pedido servido");
                        alert.show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error de BBDD");
                        alert.setContentText("No se pudo marcar el pedido como servido.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Selecciona un pedido de la lista");
                    alert.show();
                }

            } else if (event.getSource() == btnPendientes) {
                observablePedidos.setAll(pedidoDAO.obtenerPendientes());

            } else if (event.getSource() == btnDetalle) {
                Pedido seleccionado = listaPedidos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("detalle-view.fxml"));
                        Parent root = loader.load();

                        DetallePedidoController controller = loader.getController();
                        controller.setPedido(seleccionado);

                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setTitle("Detalle del Pedido");
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Selecciona un pedido para ver detalle");
                    alert.show();
                }
            }
        }
    }
}