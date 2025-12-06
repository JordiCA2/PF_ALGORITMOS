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
    private servicios.GestorInventario inventario;

    private int leerInt(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida.");
            }
        }
    }
    
    private double leerDouble(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida.");
            }
        }
    }
    
    // Constructor que crea gestor de pedidos independiente
    public MenuPedidos(Scanner sc) {
        this.scanner = sc;
        this.gestor = new GestorPedidos();
        this.inventario = new servicios.GestorInventario();
    }
    
    // constructor que usa inventario compartido inyectado
    public MenuPedidos(Scanner sc, GestorInventario inventario) {
        this.scanner = sc;
        this.gestor = new GestorPedidos(inventario);
        this.inventario = inventario;
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
        String id;
        while (true) {
            System.out.print("ID Pedido: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("Entrada inválida.");
                continue;
            }
            if (gestor.buscarPorId(id) != null) {
                System.out.println("❌ Ya existe un pedido con ese ID.");
                continue;
            }
            break;
        }

        String nombre;
        while (true) {
            System.out.print("Nombre del cliente: ");
            nombre = scanner.nextLine().trim();
            if (nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) break;
            System.out.println("Entrada inválida. Solo letras y espacios.");
        }

        String direccion;
        while (true) {
            System.out.print("Dirección: ");
            direccion = scanner.nextLine().trim();
            if (!direccion.isEmpty()) break;
            System.out.println("Entrada inválida.");
        }

        int opCliente;
        while (true) {
            System.out.println("Tipo de cliente: 1=VIP, 2=REGULAR");
            String in = scanner.nextLine().trim();
            if (in.equals("1") || in.equals("2")) { opCliente = Integer.parseInt(in); break; }
            System.out.println("Entrada inválida. Use 1 o 2.");
        }
        TipoCliente tipoCliente = opCliente == 1 ? TipoCliente.PREMIUM : TipoCliente.REGULAR;

        int opEntrega;
        while (true) {
            System.out.println("Tipo de entrega: 1=RÁPIDA, 2=PROGRAMADA");
            String in = scanner.nextLine().trim();
            if (in.equals("1") || in.equals("2")) { opEntrega = Integer.parseInt(in); break; }
            System.out.println("Entrada inválida. Use 1 o 2.");
        }
        TipoEntrega tipoEntrega = opEntrega == 1 ? TipoEntrega.EXPRESS : TipoEntrega.NORMAL;

        ArrayList<ItemPedido> items = new ArrayList<>();

        boolean mas;
        do {
            String cod;
            while (true) {
                cod = String.valueOf(leerInt("Código producto:"));
                if (inventario.buscarProducto(cod) != null) break;
                System.out.println("❌ Producto no existe.");
            }

            String nom;
            while (true) {
                System.out.print("Nombre producto: ");
                nom = scanner.nextLine().trim();
                if (nom.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) break;
                System.out.println("Entrada inválida. Solo letras y espacios.");
            }

        int cant = leerInt("Cantidad:");
        while (cant <= 0) {
            System.out.println("Entrada inválida. Debe ser mayor que 0.");
            cant = leerInt("Cantidad:");
        }

        double precio = leerDouble("Precio unitario:");
        while (precio <= 0) {
            System.out.println("Entrada inválida. Debe ser mayor que 0.");
            precio = leerDouble("Precio unitario:");
        }

            items.add(new ItemPedido(cod, nom, cant, precio, cant * precio));

            String cont;
            while (true) {
                System.out.print("¿Agregar otro item? (1=Sí / 0=No): ");
                cont = scanner.nextLine().trim();
                if (cont.equals("1") || cont.equals("0")) break;
                System.out.println("Entrada inválida. Use 1 o 0.");
            }
            mas = cont.equals("1");

        } while (mas);

        Pedido p = gestor.registrarPedido(id, nombre, direccion, tipoCliente, tipoEntrega, 
                items.toArray(new ItemPedido[0]));

        System.out.println("✔ Pedido registrado: " + p);
    }
}
