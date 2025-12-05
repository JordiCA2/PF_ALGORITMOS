package servicios;

import estructuras.Cola;
import modelos.Entrega;
import modelos.EstadoEntrega;

import java.time.LocalDateTime;

public class GestorEntregas {

    private Cola<Entrega> cola = new Cola<>();
    private estructuras.ListaEnlazada<Entrega> historialDia = new estructuras.ListaEnlazada<>();
    
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
        e.marcarEntregado(java.time.LocalDateTime.now());
        historialDia.agregar(e);
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
            e.setRepartidor(repartidor);
            // actualizar
        }
        System.out.println("Repartidor asignado a " + arr.length + " entregas");
    }

    public int totalEntregasDelDia() {
        return historialDia.tamano();
    }

    public double tiempoPromedioEntrega() {
        if (historialDia.tamano() == 0) {
            return 0.0;
        }
        Object[] arr = historialDia.toArray();
        double suma = 0;
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            Entrega e = (Entrega) arr[i];
            if (e.getEstado() == EstadoEntrega.ENTREGADO) {
                suma += e.tiempoEntregaMinutos();
                c++;
            }
        }
        return c == 0 ? 0.0 : suma / c;
    }

    
    public void asignarRepartidorPorDistrito(String distrito, String repartidor) {
        Object[] arr = cola.toArray();
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            Entrega e = (Entrega) arr[i];
            if (e.getDistrito().equalsIgnoreCase(distrito)) {
                e.setRepartidor(repartidor);
                c++;
            }
        }
        System.out.println("Repartidor asignado a " + c + " entregas en " + distrito);
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
        Object[] arr = historialDia.toArray();
        // conteo simple sin mapas
        String[] dist = new String[arr.length];
        int[] cnt = new int[arr.length];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            String d = ((Entrega) arr[i]).getDistrito();
            int pos = -1;
            for (int j = 0; j < k; j++) {
                if (dist[j].equalsIgnoreCase(d)) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                dist[k] = d;
                cnt[k] = 1;
                k++;
            } else {
                cnt[pos]++;
            }
        }
        System.out.println("=== Entregas por distrito (día) ===");
        for (int i = 0; i < k; i++) {
            System.out.println(dist[i] + " -> " + cnt[i]);
        }
    }

    public void eficienciaRepartidor() {
        Object[] arr = historialDia.toArray();
        String[] rep = new String[arr.length];
        int[] cnt = new int[arr.length];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            String r = ((Entrega) arr[i]).getRepartidor();
            int pos = -1;
            for (int j = 0; j < k; j++) {
                if (rep[j].equalsIgnoreCase(r)) {
                    pos = j;
                    break;
                }
            }
            if (pos == -1) {
                rep[k] = r;
                cnt[k] = 1;
                k++;
            } else {
                cnt[pos]++;
            }
        }
        System.out.println("=== Eficiencia por repartidor (entregas completadas) ===");
        for (int i = 0; i < k; i++) {
            System.out.println(rep[i] + " -> " + cnt[i]);
        }
    }

}
