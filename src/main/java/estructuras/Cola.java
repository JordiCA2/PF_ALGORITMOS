package estructuras;

public class Cola<T> {

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo(T v) { valor = v; }
    }

    private Nodo inicio;
    private Nodo fin;
    private int size;

    public Cola() {
        inicio = fin = null;
        size = 0;
    }

    // ENCOLAR (agregar al final)
    public void encolar(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            fin.sig = nuevo;
            fin = nuevo;
        }
        size++;
    }

    // DESENCOLAR (quitar del inicio)
    public T desencolar() {
        if (estaVacia()) return null;
        T valor = inicio.valor;
        inicio = inicio.sig;
        if (inicio == null) fin = null;
        size--;
        return valor;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int size() {
        return size;
    }

    // Obtener todos los elementos en orden
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Nodo aux = inicio;
        int i = 0;
        while (aux != null) {
            arr[i++] = aux.valor;
            aux = aux.sig;
        }
        return arr;
    }
}
