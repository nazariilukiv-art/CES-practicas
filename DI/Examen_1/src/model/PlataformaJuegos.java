package model;

import java.util.ArrayList;

public class PlataformaJuegos {

    private ArrayList<Videojuego> listaJuego = new ArrayList<>();


    public void anadirVideojego(Videojuego videojuego){
        for(Videojuego i: listaJuego){
            if(i.getTitulo().equalsIgnoreCase(videojuego.getTitulo())){
                System.out.println("No se ha encontardo juego");
                return;
            }else{
                listaJuego.add(videojuego);
                System.out.println("Añadido correcto");
            }
        }
    }

    public void mostrarJuegos(){
        for(Videojuego v: listaJuego){
            System.out.println(v);
        }
    }

    public void filtrarJuegos(){

    }

    public void calcularTotal(){
        double precio = 0.0;
        for(Videojuego v: listaJuego){
            precio += v.getPrecio();
        }
        System.out.println("Precio: " + precio);
    }

}


