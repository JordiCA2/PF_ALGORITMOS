package servicios;

import estructuras.ListaEnlazada;
import modelos.Categoria;

public class GestorCategorias {

    private ListaEnlazada<Categoria> categorias = new ListaEnlazada<>();

    // Agregar categoría
    public void agregarCategoria(String id, String nombre, String descripcion, String caracteristicas) {
        if (buscarPorId(id) != null) {
            System.out.println("❌ ID duplicado");
            return;
        }
        Categoria c = new Categoria(id, nombre, descripcion, caracteristicas);
        categorias.agregar(c);
    }

    
    public Categoria buscarPorId(String id) {
        for (int i = 0; i < categorias.tamano(); i++) {
            Categoria c = categorias.obtener(i);
            if (c != null && id.equals(c.getId())) {
                return c;
            }
        }
        return null;
    }


    // Buscar categoría por nombre
    public Categoria buscarCategoria(String nombre) {
        for (int i = 0; i < categorias.tamano(); i++) {
            Categoria c = categorias.obtener(i);
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    // Actualizar categoría
    public boolean actualizarCategoria(String id, String nuevoNombre, String nuevaDescripcion, String nuevasCaracteristicas) {
        Categoria c = buscarPorId(id);
        if (c == null) {
            return false;
        }
        c.setNombre(nuevoNombre);
        c.setDescripcion(nuevaDescripcion);
        c.setCaracteristicas(nuevasCaracteristicas);
        return true;
    }

// Eliminar categoría
public boolean eliminarCategoria(String nombre) {
    for (int i = 0; i < categorias.tamano(); i++) {
        if (categorias.obtener(i).getNombre().equalsIgnoreCase(nombre)) {
            categorias.eliminarPorIndice(i);   // <- uso correcto
            return true;
        }
    }
    return false;
}


    // Listar todas las categorías
    public Categoria[] listarCategorias() {
        Categoria[] arr = new Categoria[categorias.tamano()];
        for (int i = 0; i < categorias.tamano(); i++) {
            arr[i] = categorias.obtener(i);
        }
        return arr;
    }

    public int getCantidadCategorias() {
        return categorias.tamano();
    }

    public modelos.Producto[] listarProductosPorCategoria(String nombreCategoria, GestorInventario inv) {
        return inv.productosPorCategoria(nombreCategoria);
    }

    public boolean eliminarCategoria(String id, GestorInventario inv) {
        Categoria c = buscarPorId(id);
        if (c == null) {
            return false;
        }
        modelos.Producto[] arr = inv.productosPorCategoria(c.getNombre());
        if (arr.length > 0) {
            System.out.println("❌ No se puede eliminar: tiene productos");
            return false;
        }
        // reutilizar eliminarPorIndice
        for (int i = 0; i < categorias.tamano(); i++) {
            if (categorias.obtener(i).getId().equals(id)) {
                return categorias.eliminarPorIndice(i);
            }
        }
        return false;
    }

    public int numeroProductos(String nombreCategoria, GestorInventario inv) {
        return inv.productosPorCategoria(nombreCategoria).length;
    }

    public Categoria categoriaConMasProductos(GestorInventario inv) {
        Categoria mejor = null;
        int max = -1;
        for (int i = 0; i < categorias.tamano(); i++) {
            Categoria c = categorias.obtener(i);
            int n = inv.productosPorCategoria(c.getNombre()).length;
            if (n > max) {
                max = n;
                mejor = c;
            }
        }
        return mejor;
    }

    public Categoria categoriaMenorStockTotal(GestorInventario inv) {
        Categoria peor = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < categorias.tamano(); i++) {
            Categoria c = categorias.obtener(i);
            modelos.Producto[] arr = inv.productosPorCategoria(c.getNombre());
            int suma = 0;
            for (int j = 0; j < arr.length; j++) {
                suma += arr[j].getStock();
            }
            if (suma < min) {
                min = suma;
                peor = c;
            }
        }
        return peor;
    }

}
