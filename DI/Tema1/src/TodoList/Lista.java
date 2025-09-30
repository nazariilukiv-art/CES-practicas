package TodoList;

import java.util.ArrayList;
import java.util.List;

public class Lista implements ElementoPlanificador {

    private int id;
    private List<String> elementos;

    public Lista(int id) {
        this.id = id;
        this.elementos = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    public void agregarElemento(String texto) {
        elementos.add(texto);
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("lista id: " + id);
        if (elementos.isEmpty()) {
            System.out.println("  no hay nada");
        } else {
            System.out.println("cosas:");
            for (String e : elementos) {
                System.out.println("  - " + e);
            }
        }
    }

    public List<String> getElementos() {
        return elementos;
    }
}
