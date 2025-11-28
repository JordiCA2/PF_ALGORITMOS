package servicios;

import estructuras.Pila;
import modelos.Operacion;

public class GestorHistorial {

    // Singleton: todos los módulos usarán la misma instancia
    public static final GestorHistorial instancia = new GestorHistorial();

    private Pila<Operacion> historial = new Pila<>();

    // Constructor privado (solo se accede por instancia)
    private GestorHistorial() {}

    // Registrar una operación
    public void registrar(String tipo, Object antes, Object despues) {
        Operacion op = new Operacion(tipo, antes, despues);
        historial.push(op);
        System.out.println("Registrado en historial: " + op);
    }

    // Recupera y elimina la última operación
    public Operacion deshacer() {
        return historial.pop();
    }

    // Mostrar historial sin eliminarlo
    public void mostrarHistorial() {
        if (historial.estaVacia()) {
            System.out.println("No hay operaciones registradas.");
            return;
        }

        System.out.println("=== HISTORIAL DE OPERACIONES ===");

        // Pila auxiliar para no perder los datos
        Pila<Operacion> aux = new Pila<>();

        while (!historial.estaVacia()) {
            Operacion op = historial.pop();
            System.out.println(op);
            aux.push(op);
        }

        // Restaurar historial original
        while (!aux.estaVacia()) {
            historial.push(aux.pop());
        }
    }

    public boolean hayOperaciones() {
        return !historial.estaVacia();
    }

    // Limpiar toda la pila
    public void limpiar() {
        while (!historial.estaVacia()) {
            historial.pop();
        }
        System.out.println("Historial limpiado.");
    }
}
