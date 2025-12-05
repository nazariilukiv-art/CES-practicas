module com.example.pizzeriaapp {
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

    opens com.example.pizzeriaapp to javafx.fxml, java.sql;
    exports com.example.pizzeriaapp;

    opens com.example.pizzeriaapp.controller to javafx.fxml, java.sql, lombok;
    exports com.example.pizzeriaapp.controller;

    opens com.example.pizzeriaapp.model to java.sql, javafx.fxml, lombok;
    exports com.example.pizzeriaapp.model;


}