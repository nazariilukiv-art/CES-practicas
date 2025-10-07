package model;

import javax.swing.*;

public class VideojuegoAccion extends Videojuego implements Descargable {

    private int nivelViolencia;
    private  boolean modoMultijugador;

    public VideojuegoAccion(String titulo, String desarrollador, int añoLanzamiento, Double precio, String clasificacionEdad, Double tamañoGB, int nivelViolencia, boolean modoMultijugador) {
        super(titulo, desarrollador, añoLanzamiento, precio, clasificacionEdad, tamañoGB);
        this.nivelViolencia = nivelViolencia;
        this.modoMultijugador = modoMultijugador;
    }

    public int getNivelViolencia() {
        return nivelViolencia;
    }

    public void setNivelViolencia(int nivelViolencia) {
        if(nivelViolencia < 1 || nivelViolencia > 5){
            System.out.println("El nivel debe estar enter 1 y 5");
        }else{
        this.nivelViolencia = nivelViolencia;
        }
    }

    public boolean isModoMultijugador() {
        return modoMultijugador;
    }

    public void setModoMultijugador(boolean modoMultijugador) {
        this.modoMultijugador = modoMultijugador;
    }


    @Override
    public void calcularPrecioFinal() {
        double precio = getPrecio();
        if(nivelViolencia > 3){
            precio = ((super.getPrecio()*5)/100);
        };
        if (modoMultijugador == true){
            precio = ((super.getPrecio()*10)/100);
        }

        System.out.println("El precio es:" + precio);
    }

    @Override
    public String toString() {
        return super.toString() +  "VideojuegoAccion{" +
                "nivelViolencia=" + nivelViolencia +
                ", modoMultijugador=" + modoMultijugador +
                '}';
    }


    @Override
    public void calcularTiempoDescarga(double velocidadInternet) {

    }

    @Override
    public void obtenerTamañoGB() {

    }
}
