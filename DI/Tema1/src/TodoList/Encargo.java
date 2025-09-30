package TodoList;

public class Encargo {

    private String descripcion;
    private boolean completado;

    public Encargo(String descripcion) {
        this.descripcion = descripcion;
        this.completado = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void completar() {
        this.completado = true;
    }

    public void mostrarInfo() {
        String estado = completado ? "ok" : "no hecho";
        System.out.println("encargo: " + descripcion + " (" + estado + ")");
    }
}
