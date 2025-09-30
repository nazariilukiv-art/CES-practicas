package TodoList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Planificador plan = new Planificador();
        int opcion;

        do {
            System.out.println("PLANIFICADOR DE TAREAS");
            System.out.println("1 - Registrar tarea");
            System.out.println("2 - Modificar tarea");
            System.out.println("3 - Listar todas tareas y listas");
            System.out.println("4 - Completar tarea");
            System.out.println("5 - Listar tareas completadas");
            System.out.println("6 - Listar tareas incompletas");
            System.out.println("7 - Agregar persona a tarea");
            System.out.println("8 - Agregar encargo a tarea");
            System.out.println("0 - Salir");
            System.out.print("opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: registrarTarea(sc, plan); break;
                case 2: modificarTarea(sc, plan); break;
                case 3: plan.listarElementos(); break;
                case 4: completarTarea(sc, plan); break;
                case 5: plan.listarCompletadas(); break;
                case 6: plan.listarIncompletas(); break;
                case 7: agregarPersona(sc, plan); break;
                case 8: agregarEncargo(sc, plan); break;
                case 0: System.out.println("saliendo"); break;
                default: System.out.println("opcion no valida");
            }

        } while(opcion != 0);

        sc.close();
    }

    private static void registrarTarea(Scanner sc, Planificador plan) {
        System.out.print("tipo tarea ocio o trabajo (O/T): ");
        String tipo = sc.nextLine().toUpperCase();

        System.out.print("id: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("titulo: ");
        String titulo = sc.nextLine();
        System.out.print("descripcion: ");
        String desc = sc.nextLine();
        System.out.print("fecha: ");
        String fecha = sc.nextLine();
        System.out.print("prioridad (BAJA, MEDIA, ALTA): ");
        Prioridad prio = Prioridad.valueOf(sc.nextLine().toUpperCase());

        if(tipo.equals("O")) {
            System.out.print("ubicacion: ");
            String ubi = sc.nextLine();
            System.out.print("presupuesto: ");
            double pre = sc.nextDouble(); sc.nextLine();
            TareaOcio t = new TareaOcio(id,titulo,desc,fecha,prio,ubi,pre);
            plan.agregarElemento(t);
        } else if(tipo.equals("T")) {
            System.out.print("proyecto: ");
            String proy = sc.nextLine();
            TareaTrabajo t = new TareaTrabajo(id,titulo,desc,fecha,prio,proy);
            plan.agregarElemento(t);
        } else {
            System.out.println("tipo no valido");
            return;
        }
        System.out.println("tarea guardada");
    }

    private static void modificarTarea(Scanner sc, Planificador plan) {
        System.out.print("id tarea a modificar: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("campo a cambiar (titulo, descripcion, fecha, prioridad): ");
        String campo = sc.nextLine();
        System.out.print("nuevo valor: ");
        String valor = sc.nextLine();
        plan.modificarTarea(id,campo,valor);
        System.out.println("modificacion hecha");
    }

    private static void completarTarea(Scanner sc, Planificador plan) {
        System.out.print("id tarea a completar: ");
        int id = sc.nextInt(); sc.nextLine();
        plan.completarTarea(id);
    }

    private static void agregarPersona(Scanner sc, Planificador plan) {
        System.out.print("id tarea: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("nombre persona: ");
        String nombre = sc.nextLine();
        System.out.print("email: ");
        String email = sc.nextLine();
        Persona p = new Persona(nombre,email);
        plan.agregarPersonaTarea(id,p);
    }

    private static void agregarEncargo(Scanner sc, Planificador plan) {
        System.out.print("id tarea: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("descripcion encargo: ");
        String desc = sc.nextLine();
        Encargo e = new Encargo(desc);
        plan.agregarEncargoTarea(id,e);
    }
}
