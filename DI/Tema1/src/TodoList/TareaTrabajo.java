package TodoList;

public class TareaTrabajo extends Tarea {

    private String proyecto;

    public TareaTrabajo(int id, String titulo, String descripcion, String fecha, Prioridad prioridad,
                        String proyecto) {
        super(id, titulo, descripcion, fecha, prioridad);
        this.proyecto = proyecto;
    }

    public String getProyecto() { return proyecto; }
    public void setProyecto(String proyecto) { this.proyecto = proyecto; }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("proyecto: " + proyecto);
    }
}
