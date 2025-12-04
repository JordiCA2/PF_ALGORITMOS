package interfaz;

import java.util.Scanner;

public class MenuPrincipal {

    private Scanner scanner;             // ← FALTABA ESTO
    private MenuInventario menuInventario;
    private MenuPedidos menuPedidos;
    private MenuReportes menuReportes;

    public MenuPrincipal(Scanner scanner) {
        this.scanner = scanner;          // ← GUARDARLO COMO ATRIBUTO
        this.menuInventario = new MenuInventario(scanner);
        this.menuPedidos = new MenuPedidos(scanner);
        this.menuReportes = new MenuReportes(scanner);
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN TPP =====");
            System.out.println("1. Inventario");
            System.out.println("2. Pedidos");
            System.out.println("3. Reportes");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> menuInventario.mostrar();
                case 2 -> menuPedidos.mostrar();
                case 3 -> menuReportes.mostrar();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }
}
