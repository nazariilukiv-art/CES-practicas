module com.example.folmurario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires lombok;
    requires java.sql;

    opens com.example.folmurario to javafx.fxml, java.sql;
    exports com.example.folmurario;
    exports com.example.folmurario.controller;
    opens com.example.folmurario.controller to javafx.fxml, java.sql;


    opens com.example.folmurario.model to lombok, java.sql;
    exports com.example.folmurario.model;

}