package interfaz;

import modelos.*;
import servicios.GestorPedidos;
import servicios.GestorInventario;
import java.util.ArrayList;
import java.util.Scanner;

// Menu de pedidos
public class MenuPedidos {

    private Scanner scanner;
    private GestorPedidos gestor;

    // Constructor que crea gestor de pedidos independiente
    public MenuPedidos(Scanner sc) {
        this.scanner = sc;
        this.gestor = new GestorPedidos();
    }
    
    // constructor que usa inventario compartido inyectado
    public MenuPedidos(Scanner sc, GestorInventario inventario) {
        this.scanner = sc;
        this.gestor = new GestorPedidos(inventario);
    }

    // Muestra menu de pedidos y ejecuta acciones
    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- MENÚ PEDIDOS ---");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Atender siguiente pedido");
            System.out.println("3. Listar pedidos pendientes");
            System.out.println("4. Buscar pedido por ID");
            System.out.println("5. Ver estadísticas");
            System.out.println("6. Volver");
            System.out.print("Opción: ");

            String input = scanner.nextLine();
            try {
                op = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                op = -1;
            }
            
            switch (op) {
                case 1 ->
                    registrarPedido();
                case 2 ->
                    gestor.atenderSiguientePedido();
                case 3 ->
                    gestor.listarPedidoPendientes();
                case 4 ->
                    buscarPedidoPorId();
                case 5 ->
                    verEstadisticas();
            }

        } while (op != 6);
    }

    
    // Busca y muestra un pedido por ID
    private void buscarPedidoPorId() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        Pedido p = gestor.buscarPorId(id);
        System.out.println(p != null ? p : "❌ No encontrado");
    }

    // Muestra estadisticas de pedidos
    private void verEstadisticas() {
        System.out.println("Premium: " + gestor.totalPedidosPorTipo(TipoCliente.PREMIUM));
        System.out.println("Regular: " + gestor.totalPedidosPorTipo(TipoCliente.REGULAR));
        Pedido max = gestor.pedidoMayorValor();
        System.out.println("Mayor valor: " + (max != null ? max : "N/A"));
        System.out.println("Promedio de productos/pedido: " + gestor.promedioProductosPorPedido());
    }

    // Registra un nuevo pedido con items
    private void registrarPedido() {
        System.out.print("ID Pedido: ");
        String id = scanner.nextLine();

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.println("Tipo de cliente: 1=VIP, 2=REGULAR");
        TipoCliente tipoCliente = scanner.nextLine().equals("1") ? TipoCliente.PREMIUM : TipoCliente.REGULAR;

        System.out.println("Tipo de entrega: 1=RÁPIDA, 2=PROGRAMADA");
        TipoEntrega tipoEntrega = scanner.nextLine().equals("1") ? TipoEntrega.EXPRESS : TipoEntrega.NORMAL;

        ArrayList<ItemPedido> items = new ArrayList<>();

        boolean mas;
        do {
            System.out.print("Código producto: ");
            String cod = scanner.nextLine();

            System.out.print("Nombre producto: ");
            String nom = scanner.nextLine();

            System.out.print("Cantidad: ");
            int cant = Integer.parseInt(scanner.nextLine());

            System.out.print("Precio unitario: ");
            double precio = Double.parseDouble(scanner.nextLine());

            items.add(new ItemPedido(cod, nom, cant, precio, cant * precio));

            System.out.print("¿Agregar otro item? (1=Sí / 0=No): ");
            mas = scanner.nextLine().equals("1");

        } while (mas);

        Pedido p = gestor.registrarPedido(id, nombre, direccion, tipoCliente, tipoEntrega, 
                items.toArray(new ItemPedido[0]));

        System.out.println("✔ Pedido registrado: " + p);
    }
}
