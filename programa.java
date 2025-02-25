import java.util.*;

class Tarea {
    private String descripcion;
    private String prioridad;
    private boolean completada;

    public Tarea(String descripcion, String prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = false;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void marcarComoCompletada() {
        this.completada = true;
    }

    public boolean estaCompletada() {
        return completada;
    }

    @Override
    public String toString() {
        return "[ " + (completada ? "✔" : "✖") + " ] " + descripcion + " - Prioridad: " + prioridad;
    }
}

public class OrganizadorTareas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Tarea> tareas = new ArrayList<>();

        while (true) {
            System.out.println("\n📌 ORGANIZADOR DE TAREAS 📌");
            System.out.println("1. Agregar nueva tarea");
            System.out.println("2. Ver lista de tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese la prioridad (Alta, Media, Baja): ");
                    String prioridad = scanner.nextLine();
                    tareas.add(new Tarea(descripcion, prioridad));
                    System.out.println("✅ Tarea agregada correctamente.");
                    break;

                case 2:
                    if (tareas.isEmpty()) {
                        System.out.println("📭 No hay tareas registradas.");
                    } else {
                        System.out.println("\n📋 Lista de tareas:");
                        tareas.stream()
                                .sorted(Comparator.comparing(Tarea::getPrioridad, (p1, p2) -> {
                                    List<String> orden = Arrays.asList("Alta", "Media", "Baja");
                                    return Integer.compare(orden.indexOf(p1), orden.indexOf(p2));
                                }))
                                .forEach(t -> System.out.println(t));
                    }
                    break;

                case 3:
                    if (tareas.isEmpty()) {
                        System.out.println("📭 No hay tareas para completar.");
                    } else {
                        System.out.println("Seleccione el número de la tarea a completar:");
                        for (int i = 0; i < tareas.size(); i++) {
                            System.out.println((i + 1) + ". " + tareas.get(i));
                        }
                        int index = scanner.nextInt() - 1;
                        if (index >= 0 && index < tareas.size()) {
                            tareas.get(index).marcarComoCompletada();
                            System.out.println("✅ Tarea completada.");
                        } else {
                            System.out.println("❌ Opción inválida.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("👋 Saliendo del organizador de tareas. ¡Éxito en tu día!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Opción no válida. Intente de nuevo.");
            }
        }
    }
}
