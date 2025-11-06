package com.example.folmurario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("formularioAnterior-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Folmulario APP");
        stage.setScene(scene);
        stage.show();
    }
}
