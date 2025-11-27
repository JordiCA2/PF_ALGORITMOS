package servicios;

import estructuras.ArbolBinarioBusqueda;
import modelos.Producto;

public class GestorInventario {
    private final ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
    
    public boolean agregarProducto(String codigo, String nombre, String categoria, double precio, int stock, boolean esBiodegradable, String materialesReciclados) {
        Producto p = new Producto(codigo, nombre, categoria, precio, stock, esBiodegradable, materialesReciclados);
        return arbol.insertar(p);
    }
    
    public boolean actualizarStock(String codigo, int nuevoStock) {
        Producto p = arbol.buscar(codigo);
        if (p == null) return false;
        p.setStock(nuevoStock);
        return true;
    }
    
    public Producto buscarProducto(String codigo) {
        return arbol.buscar(codigo);
    }
    
    public void generarReporteStockBajo(int limite) {
        Producto[] arr = arbol.buscarStockCritico(limite);
        for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
    }
    
    public void listarTodo() {
        Producto[] arr = arbol.obtenerInOrder();
        for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
    }
    
    public Producto[] productosPorRangoPrecio(double min, double max) {
        return arbol.buscarPorRangoPrecio(min, max);
    }
}
