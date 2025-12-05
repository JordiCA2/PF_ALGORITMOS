package servicios;

import estructuras.Pila;
import modelos.Operacion;
import modelos.TipoOperacion;

// Gestor de historial, singleton con pila de operaciones
public class GestorHistorial {

    // Singleton: todos los módulos usarán la misma instancia
    public static final GestorHistorial instancia = new GestorHistorial();

    private Pila<Operacion> historial = new Pila<>();

    // Constructor privado (solo se accede por instancia)
    private GestorHistorial() {}

    // Registrar una operación
    public void registrar(TipoOperacion tipo, String descripcion, String detalles, String usuario, Object antes, Object despues) {
        Operacion op = new Operacion(tipo, descripcion, detalles, usuario, antes, despues);
        historial.push(op);
        System.out.println("Registrado en historial: " + op);
    }

    // Recupera y elimina la última operación
    public Operacion deshacer() {
        return historial.pop();
    }

    // Deshace la ultima operacion y revierte efectos
    public boolean deshacerUltima(GestorInventario inv, GestorPedidos ped) {
        Operacion op = historial.pop();
        if (op == null) {
            return false;
        }
        switch (op.getTipoOp()) {
            case AGREGAR -> {
                if (op.getDespues() instanceof modelos.Producto prod) {
                    return inv.eliminarProducto(prod.getCodigo());
                }
            }
            case ELIMINAR -> {
                if (op.getAntes() instanceof modelos.Producto prod) {
                    return inv.agregarProducto(prod.getCodigo(), prod.getNombre(), prod.getCategoria(), prod.getPrecio(), prod.getStock(), prod.isEsBiodegradable(), prod.getMaterialesReciclados());
                }
            }
            case MODIFICAR -> {
                if (op.getAntes() instanceof modelos.Producto prod) {
                    modelos.Producto actual = inv.buscarProducto(prod.getCodigo());
                    if (actual == null) {
                        return false;
                    }
                    actual.setNombre(prod.getNombre());
                    actual.setCategoria(prod.getCategoria());
                    actual.setPrecio(prod.getPrecio());
                    actual.setStock(prod.getStock());
                    actual.setEsBiodegradable(prod.isEsBiodegradable());
                    actual.setMaterialesReciclados(prod.getMaterialesReciclados());
                    return true;
                }
            }
            case PROCESAR_PEDIDO -> {
                if (op.getDespues() instanceof modelos.Pedido pedido) {
                    Object[] arr = pedido.getListaItems().toArray();
                    for (int i = 0; i < arr.length; i++) {
                        modelos.ItemPedido it = (modelos.ItemPedido) arr[i];
                        modelos.Producto prod = inv.buscarProducto(it.getCodigoProducto());
                        if (prod != null) {
                            inv.actualizarStock(prod.getCodigo(), prod.getStock() + it.getCantidad());
                        }
                    }
                    pedido.setEstado(modelos.EstadoPedido.PENDIENTE);
                    return true;
                }
            }
            case CAMBIAR_ESTADO -> {
                if (op.getAntes() instanceof modelos.Pedido pedido) {
                    // restaurar estado anterior
                    pedido.setEstado(((modelos.Pedido) op.getAntes()).getEstado());
                    return true;
                }
            }
        }
        return false;
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

    // Muestra las ultimas n operaciones sin perder la pila
    public void mostrarUltimas(int n) {
        if (n <= 0) {
            return;
        }
        estructuras.Pila<Operacion> aux = new estructuras.Pila<>();
        int mostradas = 0;
        while (!historial.estaVacia() && mostradas < n) {
            Operacion op = historial.pop();
            System.out.println(op);
            aux.push(op);
            mostradas++;
        }
        while (!aux.estaVacia()) {
            historial.push(aux.pop());
        }
    }

    // Busca operaciones por tipo y retorna un arreglo
    public Operacion[] buscarPorTipo(TipoOperacion t) {
        estructuras.Pila<Operacion> aux = new estructuras.Pila<>();
        java.util.ArrayList<Operacion> res = new java.util.ArrayList<>();
        while (!historial.estaVacia()) {
            Operacion op = historial.pop();
            if (op.getTipoOp() == t) {
                res.add(op);
            }
            aux.push(op);
        }
        while (!aux.estaVacia()) {
            historial.push(aux.pop());
        }
        return res.toArray(new Operacion[0]);
    }

    // Indica si hay operaciones registradas
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
