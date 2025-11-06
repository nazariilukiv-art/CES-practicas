package com.example.folmurario.controller;

// --- IMPORTACIONES AÑADIDAS PARA LA BASE DE DATOS ---
import com.example.folmurario.model.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// ---------------------------------------------------


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import com.example.folmurario.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonEliminar;

    @FXML
    private CheckBox checkDisponibilidad;

    @FXML
    private ComboBox<Integer> comboEdad;

    @FXML
    private BorderPane panelGeneral;

    @FXML
    private FlowPane parteDerecha;

    @FXML
    private RadioButton radioFemenino;

    @FXML
    private RadioButton radioMasculino;

    @FXML
    private TextField texfieldNombre;

    @FXML
    private TextField textfieldCorreo;

    @FXML
    private TextField textfieldLocalizacion;

    @FXML
    private ToggleButton toggleLista;

    private ToggleGroup grupoGenero;

    private ObservableList<Integer> listaEdades;
    private ObservableList<Usuario> listaUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instancias();
        initGUI();
        acciones();
    }

    private void acciones() {
        botonAgregar.setOnAction(new ManejoActions());
        botonEliminar.setOnAction(new ManejoActions());
        checkDisponibilidad.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean oldValue, Boolean newValue) {
                botonAgregar.setDisable(!newValue);

            }
        });
        toggleLista.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean aBoolean, Boolean t1) {
                if (t1) {
                    panelGeneral.setRight(parteDerecha);
                } else {
                    panelGeneral.setRight(null);
                }
            }
        });
    }

    private void instancias() {
        grupoGenero = new ToggleGroup();
        grupoGenero.getToggles().addAll(radioMasculino, radioFemenino);
        listaEdades = FXCollections.observableArrayList();
        listaUsuarios = FXCollections.observableArrayList();
        for (int i = 18; i < 91; i++) {
            listaEdades.add(i);
        }
    }

    private void initGUI() {
        comboEdad.setItems(listaEdades);
        botonAgregar.setDisable(!checkDisponibilidad.isSelected());
        if (toggleLista.isSelected()) {
            panelGeneral.setRight(parteDerecha);
        } else {
            panelGeneral.setRight(null);
        }
    }


    private int insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, localizacion, genero, edad, disponibilidad) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        int generatedId = -1; // Para guardar el ID

        // ¡CAMBIO CLAVE! Añadimos 'Statement.RETURN_GENERATED_KEYS'
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            // ... (el resto de tus pstmt.set...)
            pstmt.setString(3, usuario.getLocalizacion());
            pstmt.setString(4, usuario.getGenero());
            pstmt.setInt(5, usuario.getEdad());
            pstmt.setBoolean(6, usuario.isDisponibilidad());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("¡Usuario insertado!");

                // --- ¡AQUÍ ESTÁ LA MAGIA! ---
                // Pedimos las llaves generadas
                try (java.sql.ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1); // Obtenemos el ID
                        System.out.println("ID Generado: " + generatedId);
                    }
                }
                // -----------------------------
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario.");
            e.printStackTrace();
        }

        // Devolvemos el ID
        return generatedId;
    }


    private void eliminarUsuario(int id) {
        // ¡Consulta SQL mucho más segura y rápida!
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asignamos el ID
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("¡Usuario con ID " + id + " eliminado con éxito!");
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario.");
            e.printStackTrace();
        }
    }


    class ManejoActions implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == botonAgregar) {


                if (!texfieldNombre.getText().isEmpty()
                        && !textfieldCorreo.getText().isEmpty()
                        && !textfieldLocalizacion.getText().isEmpty()
                        && grupoGenero.getSelectedToggle() != null
                        && comboEdad.getSelectionModel().getSelectedItem() >= 0
                ) {
                    String nombre = texfieldNombre.getText();
                    String correo = textfieldCorreo.getText();
                    String localizacion = textfieldLocalizacion.getText();
                    String genero = ((RadioButton) grupoGenero.getSelectedToggle()).getText();
                    boolean disponibilidad = checkDisponibilidad.isSelected();
                    int edad = comboEdad.getSelectionModel().getSelectedItem();
                    Usuario usuario = new Usuario(
                            nombre, correo, localizacion, genero, edad, disponibilidad
                    );
                    listaUsuarios.add(usuario);

                    // limpiar todos los datso
                } else {
                    System.out.println("Error, faltan datoss");
                }


            } else if (actionEvent.getSource() == botonEliminar) {

            }


            if (actionEvent.getSource() == botonAgregar) {
                if (!texfieldNombre.getText().isEmpty()
                    // ... (tus otras comprobaciones)
                ) {
                    String nombre = texfieldNombre.getText();
                    // ... (obtienes el resto de datos)

                    // 1. Creamos el usuario (con ID nulo)
                    Usuario usuario = new Usuario(
                            nombre, correo, localizacion, genero, edad, disponibilidad
                    );

                    // 2. Insertamos Y CAPTURAMOS el ID devuelto
                    int nuevoId = insertarUsuario(usuario);

                    if (nuevoId != -1) {
                        // 3. ¡ACTUALIZAMOS el objeto en Java!
                        usuario.setId(nuevoId);

                        // 4. Ahora lo añadimos a la lista local
                        listaUsuarios.add(usuario);
                        System.out.println("Usuario añadido a la lista local con ID: " + usuario.getId());
                    }

                    // limpiar todos los datso
                } else {
                    System.out.println("Error, faltan datoss");
                }
            }

        }
    }
}