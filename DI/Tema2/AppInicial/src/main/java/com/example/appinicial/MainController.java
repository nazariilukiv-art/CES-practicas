package com.example.appinicial;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label labelSaludo;

    @FXML
    public TextField textFieldNombre;

    @FXML
    private Button bottonPulsar, buttonVaciar, buttonLimpiar;

    private DropShadow sombra;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ejecutado directamente en la acosiacion de la Stage
        instancias();
        initGUI();
        acciones();


    }


    private void initGUI() {
        // Personaliza las partes de la UI
        // bottonPulsar.setEffect(sombra);
    }

    private void instancias() {
        sombra = new DropShadow();

    }




    private void acciones(){

        /*
        bottonPulsar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // el metodo a ejecutar cuando el botton sea pulsado
                String nombre = textFieldNombre.getText();
                if(nombre.isBlank()){
                    System.out.println("Por favor rellena nombre");
                }else{
                    labelSaludo.setText(String.format("Enorabuena %s has completado el  1er ejercicio ", nombre));
                }
            }
        });
        buttonVaciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                labelSaludo.setText("");
                textFieldNombre.clear();
            }
        });
        */
        buttonVaciar.setOnAction(new ManejoAccion());
        bottonPulsar.setOnAction(new ManejoAccion());




        /*
        bottonPulsar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottonPulsar.setEffect(sombra);
            }
        });

        bottonPulsar.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bottonPulsar.setEffect(null);
            }
        });

         */

        bottonPulsar.setOnMousePressed(new ManejoRaton());
        bottonPulsar.setOnMouseReleased(new ManejoRaton());

        bottonPulsar.setOnMouseExited(new ManejoRaton());
        bottonPulsar.setOnMouseEntered(new ManejoRaton());

        buttonVaciar.setOnMouseExited(new ManejoRaton());
        buttonVaciar.setOnMouseEntered(new ManejoRaton());


        buttonLimpiar.addEventHandler(ActionEvent.ACTION, new ManejoAccion());



    }




    class ManejoAccion implements EventHandler<ActionEvent>{


        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("Pulsar boton");
            // que boton se ha pulsado?
            if(actionEvent.getSource()==buttonVaciar){
                String nombre = textFieldNombre.getText();
                if(nombre.isBlank()){
                    System.out.println("Por favor rellena nombre");
                }else{
                    labelSaludo.setText(String.format("Enorabuena %s has completado el  1er ejercicio ", nombre));
                    textFieldNombre.clear();
                }

            }else if(actionEvent.getSource()==bottonPulsar){
                labelSaludo.setText("");
                textFieldNombre.clear();
            } else if (actionEvent.getSource() == buttonLimpiar) {
                textFieldNombre.clear();

            }
        }



    }



    class ManejoRaton implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent mouseEvent) {

            Button boton = (Button) mouseEvent.getSource();

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED){
                boton.setEffect(sombra);
                if (boton == bottonPulsar){
                    boton.setCursor(Cursor.OPEN_HAND);
                }
            }else if( mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED){
                boton.setEffect(null);
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                bottonPulsar.setCursor(Cursor.CLOSED_HAND);
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                bottonPulsar.setCursor(Cursor.OPEN_HAND);
            }

        }







    }






}
