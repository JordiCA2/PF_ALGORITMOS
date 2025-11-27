package servicios;

import estructuras.ColaConPrioridad;
import modelos.*;

// Servicio para registrar, listar y atender pedidos por prioridad
public class GestorPedidos {
    private final ColaConPrioridad cola = new ColaConPrioridad();
    
    // Registro de un pedido, agrega sus items y lo encola segun prioridad
    public Pedido registrarPedido(String id, String nombreCliente, String direccion, TipoCliente tipoCliente,
            TipoEntrega tipoEntrega, ItemPedido[] items) {
        Pedido p = new Pedido(id, nombreCliente, direccion, tipoCliente, tipoEntrega);
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                p.agregarItem(items[i]);
            }
        }
        cola.encolar(p);
        return p;
    }
    
    // Atiende el siguiente pedido (mayor prioridad)
    public boolean atenderSiguientePedido() {
        Pedido p = cola.desencolar();
        if (p == null) return false;
        p.setEstado(EstadoPedido.EN_PROCESO);
        System.out.println("Atendiendo pedido de " + p.getNombreCliente() + " con prioridad " + p.getPrioridad());
        return true;
    }
    
    // Lista pedidos pendientes en orden de prioridad
    public void listarPedidoPendientes() {
        Pedido[] arr = cola.pendientes();
        for (int i = 0; i < arr.length; i++) {
            Pedido p = arr[i];
            System.out.println(p);
        }
    }
}
