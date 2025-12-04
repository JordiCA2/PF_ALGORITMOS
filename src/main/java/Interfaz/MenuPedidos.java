package interfaz;

import modelos.*;
import servicios.GestorPedidos;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuPedidos {

    private Scanner scanner;
    private GestorPedidos gestor = new GestorPedidos();

    public MenuPedidos(Scanner sc) {
        this.scanner = sc;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- MENÚ PEDIDOS ---");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Atender siguiente pedido");
            System.out.println("3. Listar pedidos pendientes");
            System.out.println("4. Volver");
            System.out.print("Opción: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> registrarPedido();
                case 2 -> gestor.atenderSiguientePedido();
                case 3 -> gestor.listarPedidoPendientes();
            }

        } while (op != 4);
    }

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
