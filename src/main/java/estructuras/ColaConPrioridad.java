package estructuras;

import modelos.Pedido;

// Insertar ordenado por prioridad (desc), estable para prioridades iguales
public class ColaConPrioridad {
    private static class Nodo {
        Pedido dato;
        Nodo siguiente;
        Nodo(Pedido d) { this.dato = d; }
    }
    
    private Nodo cabeza;
    private int tamano;
    
    // Insertar manteniendo orden por prioridad y estabilidad en empates
    public void encolar(Pedido p) {
        Nodo n = new Nodo(p);
        if (cabeza == null || p.getPrioridad() > cabeza.dato.getPrioridad()) {
            n.siguiente = cabeza;
            cabeza = n;
            tamano++;
            return;
        }
        
        Nodo cur = cabeza;
        while (cur.siguiente != null && cur.siguiente.dato.getPrioridad() >= p.getPrioridad()) {
            cur = cur.siguiente;
        }
        n.siguiente = cur.siguiente;
        cur.siguiente = n;
        tamano++;
    }
    
    // Extrae el pedido al frente (mayor prioridad)
    public Pedido desencolar() {
        if (cabeza == null) return null;
        Pedido p = cabeza.dato;
        cabeza = cabeza.siguiente;
        tamano--;
        return p;
    }
    
    // Devuelve el siguiente sin extraerlo
    public Pedido verProximo() {
        return cabeza == null ? null : cabeza.dato;
    }
    
    public boolean estaVacia() { return cabeza == null; }
    
    public int tamano() { return tamano; }
    
    public Pedido[] pendientes() {
        Pedido[] arr = new Pedido[tamano];
        Nodo cur = cabeza; int i = 0;
        while (cur != null) { arr[i++] = cur.dato; cur = cur.siguiente; }
        return arr;
    }
}
