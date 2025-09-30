package TodoList;

import java.util.ArrayList;
import java.util.List;

public class Planificador {

    private List<ElementoPlanificador> elementos;

    public Planificador() {
        this.elementos = new ArrayList<>();
    }

    public void agregarElemento(ElementoPlanificador elem) {
        elementos.add(elem);
    }

    public ElementoPlanificador buscarPorId(int id) {
        for (ElementoPlanificador e : elementos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Tarea> obtenerTareas() {
        List<Tarea> tareas = new ArrayList<>();
        for (ElementoPlanificador e : elementos) {
            if (e instanceof Tarea) tareas.add((Tarea) e);
        }
        return tareas;
    }

    public void listarElementos() {
        if (elementos.isEmpty()) {
            System.out.println("no hay nada guardado");
        } else {
            for (ElementoPlanificador e : elementos) {
                e.mostrarDetalle();
                System.out.println("--------");
            }
        }
    }

    public void listarCompletadas() {
        boolean hay = false;
        for (Tarea t : obtenerTareas()) {
            if (t.isCompletada()) {
                t.mostrarDetalle();
                System.out.println("--------");
                hay = true;
            }
        }
        if (!hay) System.out.println("no hay tareas completadas");
    }

    public void listarIncompletas() {
        boolean hay = false;
        for (Tarea t : obtenerTareas()) {
            if (!t.isCompletada()) {
                t.mostrarDetalle();
                System.out.println("--------");
                hay = true;
            }
        }
        if (!hay) System.out.println("no hay tareas incompletas");
    }

    public void completarTarea(int id) {
        ElementoPlanificador e = buscarPorId(id);
        if (e != null && e instanceof Tarea) {
            Tarea t = (Tarea) e;
            if (!t.tieneSubtareas()) {
                t.completar();
                System.out.println("tarea completada: " + t.getTitulo());
            } else {
                System.out.println("no se puede completar tiene subtareas");
            }
        } else {
            System.out.println("tarea no encontrada");
        }
    }

    public void modificarTarea(int id, String campo, String valor) {
        ElementoPlanificador e = buscarPorId(id);
        if (e != null && e instanceof Tarea) {
            Tarea t = (Tarea) e;
            switch (campo.toLowerCase()) {
                case "titulo": t.setTitulo(valor); break;
                case "descripcion": t.setDescripcion(valor); break;
                case "fecha": t.setFecha(valor); break;
                case "prioridad": t.setPrioridad(Prioridad.valueOf(valor.toUpperCase())); break;
                default: System.out.println("campo no valido o no se puede cambiar");
            }
        } else {
            System.out.println("tarea no encontrada");
        }
    }

    // agregar persona a tarea
    public void agregarPersonaTarea(int idTarea, Persona p) {
        ElementoPlanificador e = buscarPorId(idTarea);
        if (e != null && e instanceof Tarea) {
            Tarea t = (Tarea) e;
            t.agregarPersona(p);
            System.out.println("persona agregada a la tarea");
        } else {
            System.out.println("tarea no encontrada");
        }
    }

    // agregar encargo a tarea
    public void agregarEncargoTarea(int idTarea, Encargo enc) {
        ElementoPlanificador e = buscarPorId(idTarea);
        if (e != null && e instanceof Tarea) {
            Tarea t = (Tarea) e;
            t.agregarEncargo(enc);
            System.out.println("encargo agregado a la tarea");
        } else {
            System.out.println("tarea no encontrada");
        }
    }
}
