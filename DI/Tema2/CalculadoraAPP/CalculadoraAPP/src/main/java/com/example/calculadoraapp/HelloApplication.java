package com.example.calculadoraapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("calculadora-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Calculadora APP!");
        stage.setScene(scene);
        stage.show();
    }
}