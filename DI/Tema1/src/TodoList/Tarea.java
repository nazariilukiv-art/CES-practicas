package TodoList;

import java.util.ArrayList;
import java.util.List;

public abstract class Tarea implements ElementoPlanificador {

    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private Prioridad prioridad;
    private boolean completada;
    private List<Tarea> subtareas;
    private List<Persona> personas;
    private List<Encargo> encargos;

    public Tarea(int id, String titulo, String descripcion, String fecha, Prioridad prioridad) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.completada = false;
        this.subtareas = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.encargos = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public boolean isCompletada() { return completada; }
    public List<Tarea> getSubtareas() { return subtareas; }
    public List<Persona> getPersonas() { return personas; }
    public List<Encargo> getEncargos() { return encargos; }

    public boolean tieneSubtareas() { return !subtareas.isEmpty(); }
    public void agregarSubtarea(Tarea t) { subtareas.add(t); }
    public void agregarPersona(Persona p) { personas.add(p); }
    public void agregarEncargo(Encargo e) { encargos.add(e); }

    public void completar() {
        for (Tarea t : subtareas) {
            if (!t.isCompletada()) return;
        }
        for (Encargo e : encargos) {
            if (!e.isCompletado()) return;
        }
        this.completada = true;
    }

    public void mostrarDetalle() {
        System.out.println("id: " + id);
        System.out.println("titulo: " + titulo);
        System.out.println("descripcion: " + descripcion);
        System.out.println("fecha: " + fecha);
        System.out.println("prioridad: " + prioridad);
        System.out.println("completada: " + completada);

        if (!personas.isEmpty()) {
            System.out.println("personas:");
            for (Persona p : personas) {
                System.out.println("  - " + p.getNombre() + " (" + p.getEmail() + ")");
            }
        }

        if (!encargos.isEmpty()) {
            System.out.println("encargos:");
            for (Encargo e : encargos) {
                String estado = e.isCompletado() ? "completado" : "pendiente";
                System.out.println("  - " + e.getDescripcion() + " (" + estado + ")");
            }
        }

        if (!subtareas.isEmpty()) {
            System.out.println("subtareas:");
            for (Tarea t : subtareas) {
                System.out.println("  - [" + t.getId() + "] " + t.getTitulo());
            }
        }
    }
}
