package interfaz;

import servicios.GestorEntregas;
import servicios.GestorHistorial;

import java.util.Scanner;

public class MenuReportes {

    private Scanner scanner;
    private GestorEntregas entregas = new GestorEntregas();
    private GestorHistorial historial = GestorHistorial.instancia;
    
    private String pedirTexto(String etiqueta) {
        System.out.println(etiqueta + " ");
        return scanner.nextLine();
    }
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
            System.out.println("4. Asignar repartidor (todas)");
            System.out.println("5. Reagendar entrega");
            System.out.println("6. Estadísticas: total del día y promedio");
            System.out.println("7. Estadísticas: por distrito");
            System.out.println("8. Estadísticas: por repartidor");
            System.out.println("9. Volver");
            System.out.print("Opción: ");

            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1 ->
                    historial.mostrarHistorial();
                case 2 ->
                    entregas.mostrarRuta();
                case 3 ->
                    entregas.procesarEntrega();
                case 4 ->
                    entregas.asignarRepartidor(pedirTexto("Repartidor:"));
                case 5 ->
                    entregas.reagendar();
                case 6 -> {
                    System.out.println("Total día: " + entregas.totalEntregasDelDia());
                    System.out.println("Promedio: " + entregas.tiempoPromedioEntrega());
                }
                case 7 ->
                    entregas.entregasPorDistrito();
                case 8 ->
                    entregas.eficienciaRepartidor();
            }

        } while (op != 9);
    }
}
