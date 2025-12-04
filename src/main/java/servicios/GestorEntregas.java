package servicios;

import estructuras.Cola;
import modelos.Entrega;
import modelos.EstadoEntrega;

import java.time.LocalDateTime;

public class GestorEntregas {

    private Cola<Entrega> cola = new Cola<>();

    // 1. Agregar entrega a la cola
    public void agregarEntrega(Entrega e) {
        cola.encolar(e);
        System.out.println("Entrega agregada a la ruta: " + e);
    }

    // 2. Procesar siguiente entrega (FIFO)
    public void procesarEntrega() {
        Entrega e = cola.desencolar();
        if (e == null) {
            System.out.println("No hay entregas pendientes.");
            return;
        }

        e.marcarEntregado(LocalDateTime.now());
        System.out.println("Entrega realizada: " + e);
        System.out.println("Tiempo de entrega: " + e.tiempoEntregaMinutos() + " min");
    }

    // 3. Mostrar ruta del día (pendientes)
    public void mostrarRuta() {
        Object[] arr = cola.toArray();
        if (arr.length == 0) {
            System.out.println("No hay entregas en la ruta.");
            return;
        }

        System.out.println("=== Ruta del día ===");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // 4. Asignar repartidor a TODAS las entregas pendientes
    public void asignarRepartidor(String repartidor) {
        Object[] arr = cola.toArray();
        for (int i = 0; i < arr.length; i++) {
            Entrega e = (Entrega) arr[i];
            // actualizar
        }
        System.out.println("Repartidor asignado a entrega");
    }

    // 5. Reagendar entrega (mover al final)
    public void reagendar() {
        Entrega e = cola.desencolar();
        if (e == null) return;
        cola.encolar(e);
        System.out.println("Entrega movida al final de la cola: " + e);
    }

    // 6. Estadísticas
    public int totalEntregasPendientes() {
        return cola.size();
    }

    public void entregasPorDistrito() {
        Object[] arr = cola.toArray();
        System.out.println("=== Entregas por distrito ===");
        for (int i = 0; i < arr.length; i++) {
            Entrega e = (Entrega) arr[i];
            System.out.println(e.getDistrito());
        }
    }

    public void eficienciaRepartidor() {
        Object[] arr = cola.toArray();
        System.out.println("=== Repartidores asignados ===");
        for (int i = 0; i < arr.length; i++) {
            Entrega e = (Entrega) arr[i];
            System.out.println(e.getRepartidor());
        }
    }
}
