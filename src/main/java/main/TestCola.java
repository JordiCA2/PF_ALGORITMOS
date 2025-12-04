package main;

import estructuras.Cola;
import modelos.Entrega;
import modelos.Pedido;
import modelos.EstadoPedido;
import modelos.TipoCliente;
import modelos.TipoEntrega;

import java.time.LocalDateTime;

public class TestCola {

    public static void main(String[] args) {

        Cola<Entrega> cola = new Cola<>();

        // Crear pedidos con el constructor REAL de Pedido
        Pedido p1 = new Pedido("P1", "Juan Pérez", "Av. Lima 123",
                TipoCliente.REGULAR, TipoEntrega.NORMAL);

        Pedido p2 = new Pedido("P2", "Ana Gómez", "Av. Grau 404",
                TipoCliente.PREMIUM, TipoEntrega.EXPRESS);

        Pedido p3 = new Pedido("P3", "Luis Torres", "Jr. Puno 999",
                TipoCliente.REGULAR, TipoEntrega.EXPRESS);

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

        // MARCAR ENTREGA COMO COMPLETADA
        System.out.println("\n🏁 Marcando entrega como completada...");
        atendida.marcarEntregado(LocalDateTime.now());

        System.out.println("Tiempo de entrega: " + atendida.getTiempoEntregaMinutos() + " min");
        System.out.println("Estado pedido: " + atendida.getPedido().getEstado());

        System.out.println("\n📌 Cola final:");
        mostrarCola(cola);
    }

    private static void mostrarCola(Cola<Entrega> cola) {
        Object[] arr = cola.toArray();
        for (Object o : arr) {
            System.out.println(o);
        }
    }
}
