package estructuras;


// Lista simplemente enlazada generica
public class ListaEnlazada<E> {
    private static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;
        Nodo(E d) { this.dato = d; }
    }
    
    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int tamano;
    
    // Insertar al final en O(1) manteniendo referencia de cola
    public void agregar(E valor) {
        Nodo<E> n = new Nodo<>(valor);
        if (cabeza == null) {
            cabeza = n;
            cola = n;
        } else {
            cola.siguiente = n;
            cola = n;
        }
        tamano++;
    }
    
    public boolean estaVacia() { return tamano == 0; }
    
    public int tamano() { return tamano; }
    
    public E obtener(int indice) {
        if (indice < 0 || indice >= tamano) return null;
        Nodo<E> cur = cabeza;
        for (int i = 0; i < indice; i++) cur = cur.siguiente;
        return cur.dato;
    }
    
    // Convierte la lista a arreglo de Objects para iteraciones simples
    public Object[] toArray() {
        Object[] arr = new Object[tamano];
        Nodo<E> cur = cabeza;
        int i = 0;
        while (cur != null) { arr[i++] = cur.dato; cur = cur.siguiente; }
        return arr;
    }
}
