package interfaz;

import modelos.Producto;
import servicios.GestorInventario;

import java.util.Scanner;

// Menu de inventario
public class MenuInventario {

    private Scanner scanner;
    private GestorInventario inventario = new GestorInventario();

    // Constructor que usa inventario compartido inyectado
    public MenuInventario(Scanner scanner, GestorInventario inventario) {
        this.scanner = scanner;
        this.inventario = inventario;
    }

    // Muestra opciones y dirige operaciones
    public void mostrar() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ INVENTARIO ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos (orden por codigo)");
            System.out.println("3. Buscar producto");
            System.out.println("4. Actualizar stock");
            System.out.println("5. Reporte stock bajo");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Listar por stock (ascendente)");
            System.out.println("8. Listar por precio (ascedente)");
            System.out.println("9. Listar por rango de precio");
            System.out.println("10. Volver");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 ->
                    agregarProducto();
                case 2 ->
                    inventario.listarTodo();
                case 3 ->
                    buscarProducto();
                case 4 ->
                    actualizarStock();
                case 5 ->
                    reporteStockBajo();
                case 6 ->
                    eliminarProducto();
                case 7 ->
                    listarPorStockAsc();
                case 8 ->
                    listarPorPrecioAsc();
                case 9 ->
                    listarPorRangoPrecio();
            }

        } while (opcion != 10);
    }

    // Registra producto
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

    // Elimina un producto por codigo
    private void eliminarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        boolean ok = inventario.eliminarProducto(codigo);
        System.out.println(ok ? "Eliminado." : "Producto no existe.");
    }

    // busca y muestra un producto por codigo
    private void buscarProducto() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        Producto p = inventario.buscarProducto(codigo);
        System.out.println(p != null ? p : "❌ No encontrado.");
    }

    // Actualiza el stock de un producto
    private void actualizarStock() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nuevo stock: ");
        int nuevo = Integer.parseInt(scanner.nextLine());

        boolean ok = inventario.actualizarStock(codigo, nuevo);
        System.out.println(ok ? "✔ Actualizado." : "❌ Producto no existe.");
    }

    // Repore de productos con stock bajo
    private void reporteStockBajo() {
        System.out.print("Límite: ");
        int limite = Integer.parseInt(scanner.nextLine());
        inventario.generarReporteStockBajo(limite);
    }

    // Lista productos por stock ascendente
    private void listarPorStockAsc() {
        Producto[] arr = inventario.listarPorStockAsc();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // Lista productos por precio ascendente
    private void listarPorPrecioAsc() {
        Producto[] arr = inventario.listarPrecioAsc();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    // Lista productos entre un rango de precio
    private void listarPorRangoPrecio() {
        System.out.print("Precio mínimo: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Precio máximo: ");
        double max = Double.parseDouble(scanner.nextLine());
        Producto[] arr = inventario.productosPorRangoPrecio(min, max);
        if (arr.length == 0) {
            System.out.println("(sin resultados)");
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }
}
