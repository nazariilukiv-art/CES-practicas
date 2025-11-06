package com.example.folmurario.controller;

import com.example.folmurario.model.DatabaseConnector;
import com.example.folmurario.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    // --- CAMPOS DEL FORMULARIO (Inyectados desde FXML) ---
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonDetalle;
    @FXML
    private CheckBox checkDisponibilidad;
    @FXML
    private ComboBox<Integer> comboEdad;
    @FXML
    private BorderPane panelGeneral;
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

    // --- CAMPOS DE LA TABLA (Inyectados desde FXML) ---
    @FXML
    private TableView<Usuario> tablaUsuarios;

    // ==========================================================
    // --- ¡CAMBIO! ---
    // Hemos reemplazado 'colId' por 'colNumero'.
    // Usamos 'Void' porque esta columna no viene del objeto 'Usuario'.
    @FXML
    private TableColumn<Usuario, Void> colNumero;
    // ==========================================================

    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colCorreo;

    // --- VARIABLES DE ESTADO Y LISTAS ---
    private ToggleGroup grupoGenero;
    private ObservableList<Integer> listaEdades;
    private ObservableList<Usuario> listaUsuarios;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        configurarTabla(); // <-- Aquí está la magia
        initGUI();
        acciones();
        cargarUsuariosDeLaBD();
    }

    /**
     * Configura la TableView, enlazando la lista 'listaUsuarios'
     * y asociando cada columna con una propiedad del objeto Usuario.
     */
    private void configurarTabla() {
        tablaUsuarios.setItems(listaUsuarios);

        // --- ¡CAMBIO! ---
        // Ya no enlazamos 'colId'. En su lugar, le damos a 'colNumero'
        // una "Fábrica de Celdas" (CellFactory) para que sepa cómo dibujarse.
        colNumero.setCellFactory(col -> new TableCell<Usuario, Void>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    // getIndex() es la fila visual (empieza en 0)
                    // Le sumamos 1 para que cuente 1, 2, 3...
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        // Estas se quedan igual, enlazadas al objeto Usuario
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    }


    // ========================================================================
    // TODO EL RESTO DEL CÓDIGO (acciones, instancias, initGUI, limpiarCampos,
    // abrirVentanaDetalle, cargarUsuariosDeLaBD, insertarUsuario,
    // eliminarUsuario, y la clase ManejoActions)
    // SE QUEDA EXACTAMENTE IGUAL QUE ANTES.
    //
    // ¿POR QUÉ?
    // Porque tu lógica de 'Eliminar' y 'Modificar' NO depende de la columna
    // visual. Depende de esto:
    //
    // Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
    //
    // ...y luego usa 'usuarioSeleccionado.getId()'.
    //
    // El 'id' real (ej. 13) sigue guardado en el objeto 'Usuario' en la
    // 'listaUsuarios', aunque el usuario solo vea "1", "2", "3" en la tabla.
    // ¡Esto es separar la VISTA de los DATOS! Es la forma profesional.
    // ========================================================================



    private void acciones() {
        botonAgregar.setOnAction(new ManejoActions());
        botonEliminar.setOnAction(new ManejoActions());
        botonDetalle.setOnAction(new ManejoActions());

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
                    panelGeneral.setRight(tablaUsuarios);
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
            panelGeneral.setRight(tablaUsuarios);
        } else {
            panelGeneral.setRight(null);
        }
    }

    private void limpiarCampos() {
        texfieldNombre.clear();
        textfieldCorreo.clear();
        textfieldLocalizacion.clear();
        comboEdad.getSelectionModel().clearSelection();
        grupoGenero.selectToggle(null);
        checkDisponibilidad.setSelected(true);
    }

    private void abrirVentanaDetalle(Usuario usuario) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/folmurario/detalle-usuario-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            DetalleUsuarioController controller = fxmlLoader.getController();
            controller.setUsuario(usuario);
            Stage stage = new Stage();
            stage.setTitle("Detalle / Modificar Usuario (ID Real: " + usuario.getId() + ")");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error al abrir la ventana de detalle");
            e.printStackTrace();
        }
    }

    private void cargarUsuariosDeLaBD() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuariosCargados = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Cargando usuarios existentes...");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String localizacion = rs.getString("localizacion");
                String genero = rs.getString("genero");
                int edad = rs.getInt("edad");
                boolean disponibilidad = rs.getBoolean("disponibilidad");
                Usuario usuario = new Usuario(id, nombre, correo, localizacion, genero, edad, disponibilidad);
                usuariosCargados.add(usuario);
            }
            listaUsuarios.addAll(usuariosCargados);
            System.out.println("Carga finalizada. " + usuariosCargados.size() + " usuarios cargados.");
        } catch (SQLException e) {
            System.err.println("Error al cargar usuarios de la base de datos.");
            e.printStackTrace();
        }
    }

    private int insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, localizacion, genero, edad, disponibilidad) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getLocalizacion());
            pstmt.setString(4, usuario.getGenero());
            pstmt.setInt(5, usuario.getEdad());
            pstmt.setBoolean(6, usuario.isDisponibilidad());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Usuario insertado!");
                try (java.sql.ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        System.out.println("ID Real Generado: " + generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario.");
            if (e.getMessage().contains("Duplicate entry")) {
                System.err.println("Error de duplicado: Ese correo ya existe.");
            }
            e.printStackTrace();
        }
        return generatedId;
    }

    private void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Usuario con ID Real " + id + " eliminado con éxito!");
            } else {
                System.out.println("No se encontró ningún usuario con el ID Real " + id);
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
                        && comboEdad.getSelectionModel().getSelectedItem() != null
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
                    int nuevoId = insertarUsuario(usuario);
                    if (nuevoId != -1) {
                        usuario.setId(nuevoId);
                        listaUsuarios.add(usuario);
                        System.out.println("Usuario añadido a la lista local con ID Real: " + usuario.getId());
                        limpiarCampos();
                    } else {
                        System.err.println("La inserción en la base de datos falló. ID no generado. (Probablemente correo duplicado)");
                    }
                } else {
                    System.err.println("Error, faltan datos en el formulario.");
                }
            } else if (actionEvent.getSource() == botonEliminar) {
                Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
                if (usuarioSeleccionado != null) {
                    // ¡Funciona igual! Seguimos borrando por el ID real.
                    eliminarUsuario(usuarioSeleccionado.getId());
                    listaUsuarios.remove(usuarioSeleccionado);
                } else {
                    System.err.println("No has seleccionado ningún usuario de la tabla para eliminar.");
                }
            } else if (actionEvent.getSource() == botonDetalle) {
                Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
                if (usuarioSeleccionado != null) {
                    // ¡Funciona igual! Seguimos modificando por el ID real.
                    abrirVentanaDetalle(usuarioSeleccionado);
                    tablaUsuarios.refresh();
                } else {
                    System.err.println("No has seleccionado ningún usuario para ver el detalle.");
                }
            }
        }
    }
}