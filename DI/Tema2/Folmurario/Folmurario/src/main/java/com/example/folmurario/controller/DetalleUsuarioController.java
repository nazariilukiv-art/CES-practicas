package com.example.folmurario.controller;

import com.example.folmurario.model.DatabaseConnector;
import com.example.folmurario.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetalleUsuarioController {

    // --- Campos de la UI (de detalle-usuario-view.fxml) ---
    @FXML
    private Label labelId;
    @FXML
    private TextField texfieldNombre;
    @FXML
    private TextField textfieldCorreo;
    @FXML
    private TextField textfieldLocalizacion;
    @FXML
    private ComboBox<Integer> comboEdad;
    @FXML
    private RadioButton radioMasculino;
    @FXML
    private RadioButton radioFemenino;
    @FXML
    private CheckBox checkDisponibilidad;
    @FXML
    private Button botonModificar;

    private ToggleGroup grupoGenero;
    private ObservableList<Integer> listaEdades;

    // Esta es la variable CLAVE.
    // Es el objeto Usuario que recibiremos de la ventana principal.
    private Usuario usuarioActual;

    /**
     * Este método se llama al crear la ventana, PERO antes de
     * que recibamos el usuario. Es para inicializar la UI.
     */
    @FXML
    public void initialize() {
        // Inicializamos los componentes de la UI de esta ventana
        grupoGenero = new ToggleGroup();
        grupoGenero.getToggles().addAll(radioMasculino, radioFemenino);

        listaEdades = FXCollections.observableArrayList();
        for (int i = 18; i < 91; i++) {
            listaEdades.add(i);
        }
        comboEdad.setItems(listaEdades);
    }

    /**
     * Este es el método que la VENTANA PRINCIPAL llamará
     * para "pasarnos" el usuario que fue seleccionado.
     * @param usuario El usuario en el que se hizo clic.
     */
    public void setUsuario(Usuario usuario) {
        this.usuarioActual = usuario;

        // Rellenamos todos los campos de la UI con los datos del usuario
        labelId.setText(String.valueOf(usuario.getId()));
        texfieldNombre.setText(usuario.getNombre());
        textfieldCorreo.setText(usuario.getCorreo());
        textfieldLocalizacion.setText(usuario.getLocalizacion());
        comboEdad.setValue(usuario.getEdad());
        checkDisponibilidad.setSelected(usuario.isDisponibilidad());

        if (usuario.getGenero().equalsIgnoreCase("Masculino")) {
            grupoGenero.selectToggle(radioMasculino);
        } else {
            grupoGenero.selectToggle(radioFemenino);
        }
    }

    /**
     * Se llama cuando el usuario pulsa el botón "Modificar".
     */
    @FXML
    void onModificarClick(ActionEvent event) {
        // 1. Leer los datos NUEVOS de la UI
        String nuevoNombre = texfieldNombre.getText();
        String nuevoCorreo = textfieldCorreo.getText();
        String nuevaLocalizacion = textfieldLocalizacion.getText();
        int nuevaEdad = comboEdad.getValue();
        boolean nuevaDisponibilidad = checkDisponibilidad.isSelected();
        String nuevoGenero = ((RadioButton) grupoGenero.getSelectedToggle()).getText();

        // 2. Actualizar el objeto 'usuarioActual' que tenemos en memoria
        usuarioActual.setNombre(nuevoNombre);
        usuarioActual.setCorreo(nuevoCorreo);
        usuarioActual.setLocalizacion(nuevaLocalizacion);
        usuarioActual.setEdad(nuevaEdad);
        usuarioActual.setDisponibilidad(nuevaDisponibilidad);
        usuarioActual.setGenero(nuevoGenero);

        // 3. Llamar al método de la base de datos para guardar los cambios
        actualizarUsuarioEnBD(usuarioActual);

        // 4. Cerrar esta ventana
        onCerrarClick(event);
    }

    /**
     * Se llama cuando el usuario pulsa el botón "Cerrar".
     */
    @FXML
    void onCerrarClick(ActionEvent event) {
        // Obtenemos el 'Stage' (la ventana) desde el botón que se pulsó
        Stage stage = (Stage) botonModificar.getScene().getWindow();
        stage.close();
    }


    /**
     * Método privado para ejecutar el UPDATE en la base de datos.
     * @param usuario El objeto Usuario con los datos ya actualizados.
     */
    private void actualizarUsuarioEnBD(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, localizacion = ?, " +
                "genero = ?, edad = ?, disponibilidad = ? " +
                "WHERE id = ?";

        System.out.println("Actualizando usuario con ID: " + usuario.getId());

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getLocalizacion());
            pstmt.setString(4, usuario.getGenero());
            pstmt.setInt(5, usuario.getEdad());
            pstmt.setBoolean(6, usuario.isDisponibilidad());
            pstmt.setInt(7, usuario.getId()); // El ID va al final, para el WHERE

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Actualización exitosa!");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario en la BBDD.");
            e.printStackTrace();
        }
    }
}