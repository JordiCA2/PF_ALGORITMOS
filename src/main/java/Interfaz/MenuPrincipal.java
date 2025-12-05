package interfaz;

import java.util.Scanner;
import servicios.GestorInventario;

// Menu principal para navegar entre modulos
public class MenuPrincipal {

    private Scanner scanner;             
    private MenuInventario menuInventario;
    private GestorInventario inventario;
    private MenuPedidos menuPedidos;
    private MenuReportes menuReportes;

    // Constructor por defecto para crear inventario compartido
    public MenuPrincipal(Scanner scanner) {
        this.scanner = scanner;          
        this.inventario = new GestorInventario();
        this.menuInventario = new MenuInventario(scanner, inventario);
        this.menuPedidos = new MenuPedidos(scanner, inventario);
        this.menuReportes = new MenuReportes(scanner);
    }
    
    // Constructor con inventario inyectado que usa instancias compartidas
    public MenuPrincipal(Scanner scanner, GestorInventario inventario) {
        this.scanner = scanner;
        this.menuInventario = new MenuInventario(scanner, inventario);
        this.menuPedidos = new MenuPedidos(scanner, inventario);
        this.menuReportes = new MenuReportes(scanner);
    }

    // Bucle principal para la navegacion (interfaz)
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN  EcoDelivery =====");
            System.out.println("1. Inventario");
            System.out.println("2. Pedidos");
            System.out.println("3. Reportes");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = -1;
            }

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
