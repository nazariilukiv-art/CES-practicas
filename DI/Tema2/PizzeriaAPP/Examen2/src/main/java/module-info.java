module com.example.examen2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    requires lombok;

    opens com.example.examen2 to javafx.fxml;
    exports com.example.examen2;


    opens com.example.examen2.controller to javafx.fxml,java.sql;
    exports com.example.examen2.controller;

    opens com.example.examen2.model to javafx.fxml,lombok;
    exports com.example.examen2.model;

}