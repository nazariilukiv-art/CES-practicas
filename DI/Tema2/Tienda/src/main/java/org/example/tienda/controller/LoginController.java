package org.example.tienda.controller;

import org.example.tienda.HelloApplication;
import org.example.tienda.dao.UsuarioDAO;
import org.example.tienda.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtPassword;

    private UsuarioDAO usuarioDAO;

    public void initialize() {
        usuarioDAO = new UsuarioDAO();
    }

    @FXML
    public void onLogin(ActionEvent event) {
        String correo = txtCorreo.getText();
        String pass = txtPassword.getText();

        if (correo.isEmpty() || pass.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor introduce usuario y contraseña");
            return;
        }

        Usuario usuario = usuarioDAO.login(correo, pass);

        if (usuario != null) {
            // Login correcto -> Cambiar pantalla
            cambiarPantallaTienda();
        } else {
            // Usuario no existe o pass mal
            if (usuarioDAO.existeCorreo(correo)) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Contraseña incorrecta");
            } else {
                // No existe correo -> Preguntar registrar
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Usuario no encontrado");
                confirm.setHeaderText("El usuario no existe");
                confirm.setContentText("¿Quieres registrarte con estos datos?");
                Optional<ButtonType> resultado = confirm.showAndWait();

                if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                    Usuario nuevo = new Usuario(correo, pass);
                    if (usuarioDAO.registrarUsuario(nuevo)) {
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario registrado, pulsa Login de nuevo o entra directamente.");
                        cambiarPantallaTienda();
                    }
                }
            }
        }
    }

    private void cambiarPantallaTienda() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tienda-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) txtCorreo.getScene().getWindow();
            stage.setTitle("Tienda de Productos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}