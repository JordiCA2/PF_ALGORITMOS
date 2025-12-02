package estructuras;

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

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int tamano() {
        return tamano;
    }

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
