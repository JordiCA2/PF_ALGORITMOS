package servicios;

import estructuras.ArbolBinarioBusqueda;
import modelos.Producto;

// Fachada de inventario: coordina operaciones de alto nivel sobre el ABB
public class GestorInventario {
    private final ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
    
    // Crea el producto y lo inserta en el ABB
    public boolean agregarProducto(String codigo, String nombre, String categoria, double precio, int stock, boolean esBiodegradable, String materialesReciclados) {
        Producto p = new Producto(codigo, nombre, categoria, precio, stock, esBiodegradable, materialesReciclados);
        return arbol.insertar(p);
    }
    
    // Eliminar
    public boolean eliminarProducto(String codigo) {
        return arbol.eliminar(codigo);
    }
    // Actualiza el stock del producto identificado por codigo
    public boolean actualizarStock(String codigo, int nuevoStock) {
        Producto p = arbol.buscar(codigo);
        if (p == null) return false;
        p.setStock(nuevoStock);
        return true;
    }
    
    // Busca y retorna el producto por codigo
    public Producto buscarProducto(String codigo) {
        return arbol.buscar(codigo);
    }
    
    // Imprime producto con stock menor al limite
    public void generarReporteStockBajo(int limite) {
        Producto[] arr = arbol.buscarStockCritico(limite);
        for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
    }
    
    // Imprime todos los productos en orden por codigo
    public void listarTodo() {
        Producto[] arr = arbol.obtenerInOrder();
        for (int i = 0; i < arr.length; i++) System.out.println(arr[i]);
    }
    
    // Retorna productos dentro de un rango de precio
    public Producto[] productosPorRangoPrecio(double min, double max) {
        return arbol.buscarPorRangoPrecio(min, max);
    }

    // Listar por stock
    public Producto[] listarPorStockAsc() {
        Producto[] arr = arbol.obtenerInOrder();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getStock() > arr[j].getStock()) {
                    Producto tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }
    
    // Listar por precio
    public Producto[] listarPrecioAsc() {
        Producto[] arr = arbol.obtenerInOrder();
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].getPrecio() > arr[j].getPrecio()) {
                    Producto tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    public Producto[] productosPorCategoria(String categoria) {
        Producto[] arr = arbol.obtenerInOrder(); // si no está público, usar arbol.obtenerInOrder()
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCategoria().equalsIgnoreCase(categoria)) {
                count++;
            }
        }
        Producto[] out = new Producto[count];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCategoria().equalsIgnoreCase(categoria)) {
                out[k++] = arr[i];
            }
        }
        return out;
    }

}
