package interfaz;

import modelos.Producto;
import servicios.GestorInventario;

import java.util.Scanner;

// Menu de inventario
public class MenuInventario {

    private Scanner scanner;
    private GestorInventario inventario = new GestorInventario();

    private double leerDouble(String etiqueta) {
        while(true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim().replace(',','.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida.");
            }
        }
    }
    
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

    private String leerTexto(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim();
            if (s.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\\\s]+$")) {
                return s;
            }
            System.out.println("Entrada invalida. Solo letras y espacios.");
        }
    }
    
        private String leerNoVacio(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("Entrada inválida.");
        }
    }

    private boolean leerBooleanTF(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " ");
            String s = scanner.nextLine().trim().toLowerCase();
            if (s.equals("true") || s.equals("1")) return true;
            if (s.equals("false") || s.equals("0")) return false;
            System.out.println("Entrada inválida. Use true/false o 1/0.");
        }
    }
    
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

            String input = scanner.nextLine();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            
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

    private void agregarProducto() {
        int codigoNum = leerInt("Código:");
        while (codigoNum <= 0) {
            System.out.println("Entrada inválida. Debe ser entero positivo.");
            codigoNum = leerInt("Código:");
        }
        String codigo = String.valueOf(codigoNum);
        if (inventario.buscarProducto(codigo) != null) {
            System.out.println("❌ Ya existe un producto con ese código.");
            return;
        }

        String nombre = leerTexto("Nombre:");
        String categoria = leerTexto("Categoría:");

        double precio = leerDouble("Precio:");
        while (precio <= 0) {
            System.out.println("Entrada inválida. Debe ser mayor que 0.");
            precio = leerDouble("Precio:");
        }

        int stock = leerInt("Stock:");
        while (stock < 0) {
            System.out.println("Entrada inválida. No puede ser negativo.");
            stock = leerInt("Stock:");
        }

        boolean bio = leerBooleanTF("¿Es biodegradable? (true/false o 1/0):");

        String mat = leerNoVacio("Material reciclado:");

        boolean ok = inventario.agregarProducto(codigo, nombre, categoria, precio, stock, bio, mat);
        System.out.println(ok ? "✔ Producto agregado." : "❌ Ya existe un producto con ese código.");
    }

    // Elimina un producto por codigo
    private void eliminarProducto() {
        String codigo = String.valueOf(leerInt("Codigo:"));
        boolean ok = inventario.eliminarProducto(codigo);
        System.out.println(ok ? "Eliminado." : "Producto no existe.");
    }

    // busca y muestra un producto por codigo
    private void buscarProducto() {
        String codigo = String.valueOf(leerInt("Codigo:"));
        Producto p = inventario.buscarProducto(codigo);
        System.out.println(p != null ? p : "❌ No encontrado.");
    }

    // Actualiza el stock de un producto
    private void actualizarStock() {
        String codigo = String.valueOf(leerInt("Código:"));

        int nuevo = leerInt("Nuevo stock:");
        while (nuevo < 0) {
            System.out.println("Entrada inválida. No puede ser negativo.");
            nuevo = leerInt("Nuevo stock:");
        }

        boolean ok = inventario.actualizarStock(codigo, nuevo);
        System.out.println(ok ? " Actualizado." : " Producto no existe.");
    }

    // Repore de productos con stock bajo
    private void reporteStockBajo() {
        int limite = leerInt("Límite:");
        while (limite < 0) {
            System.out.println("Entrada inválida. No puede ser negativo.");
            limite = leerInt("Límite:");
        }
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
        double min = leerDouble("Precio mínimo:");
        while (min < 0) {
            System.out.println("Entrada inválida. No puede ser negativo.");
            min = leerDouble("Precio mínimo:");
        }
        double max = leerDouble("Precio máximo:");
        while (max < 0) {
            System.out.println("Entrada inválida. No puede ser negativo.");
            max = leerDouble("Precio máximo:");
        }
        if (min > max) {
            double t = min; min = max; max = t;
            System.out.println("Rango corregido: mínimo mayor que máximo.");
        }
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
