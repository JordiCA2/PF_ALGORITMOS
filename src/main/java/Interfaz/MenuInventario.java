package interfaz;

import modelos.Producto;
import servicios.GestorInventario;

import java.util.Scanner;

public class MenuInventario {

    private Scanner scanner;
    private GestorInventario inventario = new GestorInventario();

    public MenuInventario(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ INVENTARIO ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Actualizar stock");
            System.out.println("5. Reporte stock bajo");
            System.out.println("6. Volver");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> inventario.listarTodo();
                case 3 -> buscarProducto();
                case 4 -> actualizarStock();
                case 5 -> reporteStockBajo();
            }

        } while (opcion != 6);
    }

    private void agregarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Es biodegradable? (true/false): ");
        boolean bio = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Material reciclado: ");
        String mat = scanner.nextLine();

        boolean ok = inventario.agregarProducto(codigo, nombre, categoria, precio, stock, bio, mat);
        System.out.println(ok ? "✔ Producto agregado." : "❌ Ya existe un producto con ese código.");
    }

    private void buscarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        Producto p = inventario.buscarProducto(codigo);
        System.out.println(p != null ? p : "❌ No encontrado.");
    }

    private void actualizarStock() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nuevo stock: ");
        int nuevo = Integer.parseInt(scanner.nextLine());

        boolean ok = inventario.actualizarStock(codigo, nuevo);
        System.out.println(ok ? "✔ Actualizado." : "❌ Producto no existe.");
    }

    private void reporteStockBajo() {
        System.out.print("Límite: ");
        int limite = Integer.parseInt(scanner.nextLine());
        inventario.generarReporteStockBajo(limite);
    }
}
