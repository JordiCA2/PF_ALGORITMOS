package main;

import estructuras.Cola;
import modelos.Entrega;
import modelos.Pedido;
import modelos.EstadoPedido;
import java.time.LocalDateTime;

public class TestCola {

    public static void main(String[] args) {

        Cola<Entrega> cola = new Cola<>();

        // Crear pedidos
        Pedido p1 = new Pedido("P1", EstadoPedido.PENDIENTE, 50.0);
        Pedido p2 = new Pedido("P2", EstadoPedido.PENDIENTE, 75.0);
        Pedido p3 = new Pedido("P3", EstadoPedido.PENDIENTE, 30.0);

        // Crear entregas
        Entrega e1 = new Entrega("E1", p1, "Av. Lima 123", "Lima",
                "Carlos", LocalDateTime.now().plusMinutes(30));
        Entrega e2 = new Entrega("E2", p2, "Av. Grau 404", "Callao",
                "Ana", LocalDateTime.now().plusMinutes(40));
        Entrega e3 = new Entrega("E3", p3, "Jr. Puno 999", "Surco",
                "Luis", LocalDateTime.now().plusMinutes(20));

        // ENCOLAR
        cola.encolar(e1);
        cola.encolar(e2);
        cola.encolar(e3);

        System.out.println("📌 Cola inicial:");
        mostrarCola(cola);

        // DESENCOLAR
        System.out.println("\n⏳ Desencolando...");
        Entrega atendida = cola.desencolar();
        System.out.println("Atendido: " + atendida);

        System.out.println("\n📌 Cola actual:");
        mostrarCola(cola);

        // MARCAR COMO ENTREGADO
        System.out.println("\n🏁 Marcando entrega como completada...");
        atendida.marcarEntregado(LocalDateTime.now());

        System.out.println("Tiempo de entrega: " + atendida.getTiempoEntregaMinutos() + " min");
        System.out.println("Estado pedido: " + atendida.getPedido().getEstado());

        System.out.println("\n📌 Cola final:");
        mostrarCola(cola);
    }

    // Método para mostrar usando tu toArray()
    private static void mostrarCola(Cola<Entrega> cola) {
        Object[] arr = cola.toArray();
        for (Object o : arr) {
            System.out.println(o);
        }
    }
}
