package com.example.calculadoraapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {

    @FXML
    private BorderPane panelGeneral;
    @FXML
    private TextField display;
    @FXML
    private ToggleButton toggleCientifica;
    @FXML
    private GridPane panelCientifico;

    @FXML
    private Button botonCero, botonUno, botonDos, botonTres, botonCuatro,
            botonCinco, botonSeis, botonSiete, botonOcho, botonNueve, botonPunto;

    @FXML
    private Button botonSumar, botonRestar, botonMultiplicar, botonDividir, botonIgual, botonClear;

    @FXML
    private Button botonDel;

    @FXML
    private Button botonSin, botonCos, botonTan, botonLog, botonSqrt;

    private String operadorActual = "";
    private double primerOperando = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGUI();
        acciones();
    }

    private void initGUI() {
        display.setEditable(false);

        if (toggleCientifica.isSelected()) {
            panelCientifico.setVisible(true);
            panelCientifico.setManaged(true);
        } else {
            panelCientifico.setVisible(false);
            panelCientifico.setManaged(false);
        }
    }

    private void acciones() {
        ManejoAcciones manejador = new ManejoAcciones();

        botonCero.setOnAction(manejador);
        botonUno.setOnAction(manejador);
        botonDos.setOnAction(manejador);
        botonTres.setOnAction(manejador);
        botonCuatro.setOnAction(manejador);
        botonCinco.setOnAction(manejador);
        botonSeis.setOnAction(manejador);
        botonSiete.setOnAction(manejador);
        botonOcho.setOnAction(manejador);
        botonNueve.setOnAction(manejador);
        botonPunto.setOnAction(manejador);

        botonSumar.setOnAction(manejador);
        botonRestar.setOnAction(manejador);
        botonMultiplicar.setOnAction(manejador);
        botonDividir.setOnAction(manejador);
        botonIgual.setOnAction(manejador);
        botonClear.setOnAction(manejador);

        botonDel.setOnAction(manejador);

        botonSin.setOnAction(manejador);
        botonCos.setOnAction(manejador);
        botonTan.setOnAction(manejador);
        botonLog.setOnAction(manejador);
        botonSqrt.setOnAction(manejador);

        toggleCientifica.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean valorAntiguo, Boolean valorNuevo) {
                if (valorNuevo) {
                    panelCientifico.setVisible(true);
                    panelCientifico.setManaged(true);
                    panelGeneral.getScene().getWindow().setWidth(500);
                } else {
                    panelCientifico.setVisible(false);
                    panelCientifico.setManaged(false);
                    panelGeneral.getScene().getWindow().setWidth(350);
                }
            }
        });
    }

    class ManejoAcciones implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            if (actionEvent.getSource() == botonCero) {
                display.appendText("0");
            } else if (actionEvent.getSource() == botonUno) {
                display.appendText("1");
            } else if (actionEvent.getSource() == botonDos) {
                display.appendText("2");
            } else if (actionEvent.getSource() == botonTres) {
                display.appendText("3");
            } else if (actionEvent.getSource() == botonCuatro) {
                display.appendText("4");
            } else if (actionEvent.getSource() == botonCinco) {
                display.appendText("5");
            } else if (actionEvent.getSource() == botonSeis) {
                display.appendText("6");
            } else if (actionEvent.getSource() == botonSiete) {
                display.appendText("7");
            } else if (actionEvent.getSource() == botonOcho) {
                display.appendText("8");
            } else if (actionEvent.getSource() == botonNueve) {
                display.appendText("9");
            } else if (actionEvent.getSource() == botonPunto) {
                if (!display.getText().contains(".")) {
                    display.appendText(".");
                }
            } else if (actionEvent.getSource() == botonClear) {
                display.clear();
                operadorActual = "";
                primerOperando = 0;
            } else if (actionEvent.getSource() == botonDel) {
                String textoActual = display.getText();
                if (textoActual != null && !textoActual.isEmpty()) {
                    display.setText(textoActual.substring(0, textoActual.length() - 1));
                }
            } else if (actionEvent.getSource() == botonSumar) {
                manejarOperador("+");
            } else if (actionEvent.getSource() == botonRestar) {
                manejarOperador("-");
            } else if (actionEvent.getSource() == botonMultiplicar) {
                manejarOperador("*");
            } else if (actionEvent.getSource() == botonDividir) {
                manejarOperador("/");
            } else if (actionEvent.getSource() == botonIgual) {
                if (!operadorActual.isEmpty() && !display.getText().isEmpty()) {
                    double segundoOperando = Double.parseDouble(display.getText());
                    double resultado = 0;

                    switch (operadorActual) {
                        case "+":
                            resultado = primerOperando + segundoOperando;
                            break;
                        case "-":
                            resultado = primerOperando - segundoOperando;
                            break;
                        case "*":
                            resultado = primerOperando * segundoOperando;
                            break;
                        case "/":
                            if (segundoOperando != 0) {
                                resultado = primerOperando / segundoOperando;
                            } else {
                                display.setText("Error");
                                return;
                            }
                            break;
                    }
                    display.setText(String.valueOf(resultado));
                    operadorActual = "";
                }
            } else if (actionEvent.getSource() == botonSin) {
                manejarFuncionCientifica("sin");
            } else if (actionEvent.getSource() == botonCos) {
                manejarFuncionCientifica("cos");
            } else if (actionEvent.getSource() == botonTan) {
                manejarFuncionCientifica("tan");
            } else if (actionEvent.getSource() == botonLog) {
                manejarFuncionCientifica("log");
            } else if (actionEvent.getSource() == botonSqrt) {
                manejarFuncionCientifica("sqrt");
            }
        }

        private void manejarOperador(String operador) {
            if (!display.getText().isEmpty()) {
                primerOperando = Double.parseDouble(display.getText());
                operadorActual = operador;
                display.clear();
            }
        }

        private void manejarFuncionCientifica(String funcion) {
            if (!display.getText().isEmpty()) {
                double valor = Double.parseDouble(display.getText());
                double resultado = 0;
                switch (funcion) {
                    case "sin":
                        resultado = Math.sin(Math.toRadians(valor));
                        break;
                    case "cos":
                        resultado = Math.cos(Math.toRadians(valor));
                        break;
                    case "tan":
                        resultado = Math.tan(Math.toRadians(valor));
                        break;
                    case "log":
                        resultado = Math.log10(valor);
                        break;
                    case "sqrt":
                        resultado = Math.sqrt(valor);
                        break;
                }
                display.setText(String.valueOf(resultado));
            }
        }
    }
}