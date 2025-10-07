package model;

public class VideojuegoEstrategia extends Videojuego implements  Descargable{
    private int complejidad;
    private int duracionPartida;

    public VideojuegoEstrategia(String titulo, String desarrollador, int añoLanzamiento, Double precio, String clasificacionEdad, Double tamañoGB, int complejidad, int duracionPartida) {
        super(titulo, desarrollador, añoLanzamiento, precio, clasificacionEdad, tamañoGB);
        this.complejidad = complejidad;
        this.duracionPartida = duracionPartida;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        if(complejidad < 1 || complejidad > 5){
            System.out.println("El nivel debe estar enter 1 y 5");
        }else {
            this.complejidad = complejidad;
        }
    }

    public int getDuracionPartida() {
        return duracionPartida;
    }

    public void setDuracionPartida(int duracionPartida) {
        this.duracionPartida = duracionPartida;
    }

    @Override
    public void calcularPrecioFinal() {
        double precio = getPrecio();
        for (int i = 1; i>=complejidad; i++){
            precio = ((super.getPrecio()*3)/100);
            if(i == 5){
                return;
            }
        }
        System.out.println("El precio es :" + precio);
    }



    @Override
    public String toString() {
        return super.toString() + "VideojuegoEstrategia{" +
                "complejidad=" + complejidad +
                ", duracionPartida=" + duracionPartida +
                '}';
    }

    @Override
    public void calcularTiempoDescarga(double velocidadInternet) {

    }

    @Override
    public void obtenerTamañoGB() {

    }
}
