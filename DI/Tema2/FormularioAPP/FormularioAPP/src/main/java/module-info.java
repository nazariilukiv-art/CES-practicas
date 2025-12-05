module com.example.formularioapp {
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

    requires lombok;
    requires java.desktop;
    requires java.sql;


    opens com.example.formularioapp to javafx.fxml, java.sql;
    exports com.example.formularioapp;


    opens com.example.formularioapp.controller to javafx.fxml, java.sql;
    exports com.example.formularioapp.controller;

    opens com.example.formularioapp.model to lombok, java.sql;
    exports com.example.formularioapp.model;
}

