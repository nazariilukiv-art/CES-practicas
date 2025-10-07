package model;

public abstract class Videojuego {

    private String titulo;
    private String desarrollador;
    private int añoLanzamiento;
    private Double precio;
    private String clasificacionEdad;
    private Double tamañoGB;

    public Videojuego(String titulo, String desarrollador, int añoLanzamiento, Double precio, String clasificacionEdad, Double tamañoGB) {
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.añoLanzamiento = añoLanzamiento;
        this.precio = precio;
        this.clasificacionEdad = clasificacionEdad;
        this.tamañoGB = tamañoGB;
    }


    //Metodo para calcular calcular Precio Final
    public abstract void calcularPrecioFinal();


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public void setAñoLanzamiento(int añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public Double getTamañoGB() {
        return tamañoGB;
    }

    public void setTamañoGB(Double tamañoGB) {
        this.tamañoGB = tamañoGB;
    }


    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + titulo + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", añoLanzamiento=" + añoLanzamiento +
                ", precio=" + precio +
                ", clasificacionEdad='" + clasificacionEdad + '\'' +
                ", tamañoGB=" + tamañoGB +
                '}';
    }
}


