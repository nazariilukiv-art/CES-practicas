module org.example.tienda {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens org.example.tienda to javafx.fxml;
    opens org.example.tienda.controller to javafx.fxml;
    exports org.example.tienda;
}