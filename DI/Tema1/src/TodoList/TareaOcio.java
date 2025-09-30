package TodoList;

public class TareaOcio extends Tarea {

    private String ubicacion;
    private double presupuesto;

    public TareaOcio(int id, String titulo, String descripcion, String fecha, Prioridad prioridad,
                     String ubicacion, double presupuesto) {
        super(id, titulo, descripcion, fecha, prioridad);
        this.ubicacion = ubicacion;
        this.presupuesto = presupuesto;
    }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("lugar: " + ubicacion);
        System.out.println("gasto: " + presupuesto);
    }
}
