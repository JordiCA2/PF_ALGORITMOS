package interfaz;

import servicios.GestorEntregas;
import servicios.GestorHistorial;

import java.util.Scanner;

public class MenuReportes {

    private Scanner scanner;
    private GestorEntregas entregas = new GestorEntregas();
    private GestorHistorial historial = GestorHistorial.instancia;

    public MenuReportes(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrar() {
        int op;
        do {
            System.out.println("\n--- MENÚ REPORTES ---");
            System.out.println("1. Mostrar historial de operaciones");
            System.out.println("2. Mostrar entregas pendientes");
            System.out.println("3. Procesar entrega");
            System.out.println("4. Entregas por distrito");
            System.out.println("5. Repartidores asignados");
            System.out.println("6. Volver");
            System.out.print("Opción: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 -> historial.mostrarHistorial();
                case 2 -> entregas.mostrarRuta();
                case 3 -> entregas.procesarEntrega();
                case 4 -> entregas.entregasPorDistrito();
                case 5 -> entregas.eficienciaRepartidor();
            }

        } while (op != 6);
    }
}
