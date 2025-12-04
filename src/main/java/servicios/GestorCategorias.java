package servicios;

import estructuras.ListaEnlazada;
import modelos.Categoria;

public class GestorCategorias {

    private ListaEnlazada<Categoria> categorias = new ListaEnlazada<>();

    // Agregar categoría
    public void agregarCategoria(String nombre, String descripcion) {
        Categoria c = new Categoria(nombre, descripcion);
        categorias.agregar(c);
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
    public boolean actualizarCategoria(String nombre, String nuevoNombre, String nuevaDescripcion) {
        Categoria c = buscarCategoria(nombre);
        if (c == null) return false;

        c.setNombre(nuevoNombre);
        c.setDescripcion(nuevaDescripcion);
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
}
