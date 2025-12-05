package estructuras;

// Lista enlazada simple generica
public class ListaEnlazada<E> {

    private static class Nodo<E> {

        E dato;
        Nodo<E> siguiente;

        Nodo(E d) {
            this.dato = d;
        }
    }

    private Nodo<E> cabeza;
    private Nodo<E> cola;
    private int tamano;

    // Agregar al final de la lista
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

    // Indica si no hay elementos
    public boolean estaVacia() {
        return tamano == 0;
    }

    // Retorna el numero de elementos
    public int tamano() {
        return tamano;
    }

    // Obtiene el elemento por indice
    public E obtener(int indice) {
        if (indice < 0 || indice >= tamano) {
            return null;
        }
        Nodo<E> cur = cabeza;
        for (int i = 0; i < indice; i++) {
            cur = cur.siguiente;
        }
        return cur.dato;
    }

    // Convierte a arreglo en el mismo orden
    public Object[] toArray() {
        Object[] arr = new Object[tamano];
        Nodo<E> cur = cabeza;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.dato;
            cur = cur.siguiente;
        }
        return arr;
    }

    // Elimina elemento por indice
    public boolean eliminarPorIndice(int indice) {
        if (indice < 0 || indice >= tamano) {
            return false;
        }

        if (indice == 0) {
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
            tamano--;
            return true;
        }

        Nodo<E> cur = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            cur = cur.siguiente;
        }

        cur.siguiente = cur.siguiente.siguiente;

        if (cur.siguiente == null) {
            cola = cur;
        }

        tamano--;
        return true;
    }

    // Busca el primer elemento que cumple la condicion
    public E buscar(Condicion<E> condicion) {
        Nodo<E> cur = cabeza;
        while (cur != null) {
            if (condicion.test(cur.dato)) {
                return cur.dato;
            }
            cur = cur.siguiente;
        }
        return null;
    }

    // Crea una nueva lista con elementos que cumplen la condicion
    public ListaEnlazada<E> filtrar(Condicion<E> condicion) {
        ListaEnlazada<E> nueva = new ListaEnlazada<E>();
        Nodo<E> cur = cabeza;
        while (cur != null) {
            if (condicion.test(cur.dato)) {
                nueva.agregar(cur.dato);
            }
            cur = cur.siguiente;
        }
        return nueva;
    }

    // Reemplaza el elemento en un indice por uno nuevo
    public boolean reemplazar(int indice, E nuevo) {
        if (indice < 0 || indice >= tamano) {
            return false;
        }
        Nodo<E> cur = cabeza;
        for (int i = 0; i < indice; i++) {
            cur = cur.siguiente;
        }
        cur.dato = nuevo;
        return true;
    }

    // Elimina el primer elemento que cumple la condicion
    public boolean eliminarSi(Condicion<E> condicion) {
        Nodo<E> cur = cabeza;
        Nodo<E> prev = null;

        while (cur != null) {
            if (condicion.test(cur.dato)) {

                if (prev == null) { // borrar cabeza
                    cabeza = cur.siguiente;
                    if (cabeza == null) {
                        cola = null;
                    }
                } else {
                    prev.siguiente = cur.siguiente;
                    if (prev.siguiente == null) {
                        cola = prev;
                    }
                }

                tamano--;
                return true;
            }
            prev = cur;
            cur = cur.siguiente;
        }
        return false;
    }

    public static interface Condicion<T> {

        boolean test(T dato);
    }
}
