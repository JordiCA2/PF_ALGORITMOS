package estructuras;

/**
 *
 * @author Haskell
 */
public class Pila<T> {

    // Nodo interno de la pila
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> cima;  // Tope de la pila
    private int size = 0;  // Cantidad de elementos
    private final int MAX = 50; // Máximo permitido (por requerimiento del proyecto)

    // Inserta un elemento en la pila (push)
    public void push(T dato) {
        if (size == MAX) {
            // Si está lleno, eliminamos la operación más antigua
            pop();
        }

        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        size++;
    }

    // Remueve y devuelve el elemento de la cima (pop)
    public T pop() {
        if (cima == null) {
            return null;
        }

        T info = cima.dato;
        cima = cima.siguiente;
        size--;
        return info;
    }

    // Consulta si la pila está vacía
    public boolean estaVacia() {
        return cima == null;
    }

    // Devuelve el elemento de la cima sin eliminarlo
    public T cima() {
        if (cima == null) return null;
        return cima.dato;
    }

    // Limpia toda la pila
    public void limpiar() {
        cima = null;
        size = 0;
    }

    // Obtiene el tamaño actual
    public int getSize() {
        return size;
    }
}
