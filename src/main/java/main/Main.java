package main;

import servicios.GestorInventario;
import modelos.Producto;

// Prueba del modulo 1 por consola
public class Main {

    public static void main(String[] args) {
        GestorInventario gestor = new GestorInventario();
        
        gestor.agregarProducto("P100", "Vaso", "Utensilios", 3.5, 50, true, "Papel reciclado");
        gestor.agregarProducto("P200", "Bolsa", "Empaque", 0.8, 10, true, "Plastico reciclado");
        gestor.agregarProducto("P150", "Caja", "Empaque", 2.2, 5, true, "Carton reciclado");
        gestor.agregarProducto("P300", "Tenedor", "Utensilios", 1.0, 120, false, "-");
        gestor.agregarProducto("P250", "Servilleta", "Utensilios", 1.5, 8, true, "Celulosa reciclada");
        
        Producto encontrado = gestor.buscarProducto("P200");
        System.out.println(encontrado);
        Producto noEncontrado = gestor.buscarProducto("P999");
        System.out.println(noEncontrado);
        
        gestor.actualizarStock("P150", 20);
        System.out.println(gestor.buscarProducto("P150"));
        
        gestor.listarTodo();
        gestor.generarReporteStockBajo(9);
    }
    
}
