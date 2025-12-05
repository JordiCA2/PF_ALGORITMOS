package main;

import interfaz.MenuPrincipal;
import java.util.Scanner;
import servicios.GestorInventario;


public class Main {

    public static void main(String[] args) {
        // Inicia el menu principal y precarga productos
        Scanner scanner = new Scanner(System.in);
        GestorInventario inventario = new GestorInventario();
        
        // Datos de ejemplo
        inventario.agregarProducto("P100", "Vaso", "Utensilios", 3.5, 50, true, "Papel reciclado");
        inventario.agregarProducto("P200", "Bolsa", "Empaque", 0.8, 10, true, "Plastico reciclado");
        inventario.agregarProducto("P150", "Caja", "Empaque", 2.2, 5, true, "Carton reciclado");
        inventario.agregarProducto("P300", "Tenedor", "Utensilios", 1.0, 120, false, "-");
        inventario.agregarProducto("P250", "Servilleta", "Utensilios", 1.5, 8, true, "Celulosa reciclada");
        inventario.agregarProducto("P400", "Botella", "Bebidas", 4.0, 60, true, "PET reciclado");
        inventario.agregarProducto("P500", "Detergente", "Limpieza", 6.5, 30, false, "-");
        inventario.agregarProducto("P510", "Esponja", "Limpieza", 2.1, 90, true, "Fibras recicladas");
        inventario.agregarProducto("P520", "Desinfectante", "Limpieza", 7.2, 25, false, "-");
        inventario.agregarProducto("P600", "Cuaderno", "Papeleria", 3.0, 80, true, "Papel reciclado");
        inventario.agregarProducto("P610", "Lapicero", "Papeleria", 1.2, 200, false, "-");
        inventario.agregarProducto("P620", "Carpeta", "Papeleria", 2.5, 70, true, "Carton reciclado");
        
        // Inyecta inventario compartido en el menu principal
        MenuPrincipal menu = new MenuPrincipal(scanner, inventario);
        menu.mostrar();
    }
}
