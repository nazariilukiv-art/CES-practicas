package model;

public class VideojuegoRPG extends Videojuego implements Descargable{
    private boolean mundoAbierto;
    private int horasHistoriaPrincipal;


    public VideojuegoRPG(String titulo, String desarrollador, int añoLanzamiento, Double precio, String clasificacionEdad, Double tamañoGB, boolean mundoAbierto, int horasHistoriaPrincipal) {
        super(titulo, desarrollador, añoLanzamiento, precio, clasificacionEdad, tamañoGB);
        this.mundoAbierto = mundoAbierto;
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
    }

    public boolean isMundoAbierto() {
        return mundoAbierto;
    }

    public void setMundoAbierto(boolean mundoAbierto) {
        this.mundoAbierto = mundoAbierto;
    }

    public int getHorasHistoriaPrincipal() {
        return horasHistoriaPrincipal;
    }

    public void setHorasHistoriaPrincipal(int horasHistoriaPrincipal) {
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
    }


    @Override
    public void calcularPrecioFinal() {
        double precio = getPrecio();
        if(mundoAbierto == true){
            precio =((super.getPrecio()*15)/100);
        };
        if (horasHistoriaPrincipal > 10){
            precio = ((super.getPrecio()*2)/100);
        };
        System.out.println("El precio es: " + precio);
    }

    @Override
    public String toString() {
        return super.toString() + "VideojuegoRPG{" +
                "mundoAbierto=" + mundoAbierto +
                ", horasHistoriaPrincipal=" + horasHistoriaPrincipal +
                '}';
    }

    @Override
    public void calcularTiempoDescarga(double velocidadInternet) {

    }

    @Override
    public void obtenerTamañoGB() {

    }
}
