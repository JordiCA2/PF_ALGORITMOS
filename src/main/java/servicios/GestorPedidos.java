package servicios;

import estructuras.ColaConPrioridad;
import modelos.*;

// Servicio para registrar, listar y atender pedidos por prioridad
public class GestorPedidos {
    private final ColaConPrioridad cola = new ColaConPrioridad();
    private final GestorInventario inventario;
    
    public GestorPedidos() {
        this.inventario = new GestorInventario();
    }
    
    public GestorPedidos(GestorInventario inventario) {
        this.inventario = inventario;
    }
    
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
        if (p == null) {
            return false;
        }
        
        Object[] items = p.getListaItems().toArray();
        for (int i = 0; i < items.length; i++) {
            ItemPedido it = (ItemPedido) items[i];
            Producto prod = inventario.buscarProducto(it.getCodigoProducto());
            if (prod == null || prod.getStock() < it.getCantidad()) {
                System.out.println("❌ Stock insuficiente para " + it.getCodigoProducto());
                // re-encolar al frente manteniendo prioridad
                cola.encolar(p);
                p.setEstado(EstadoPedido.PENDIENTE);
                return false;
            }
        }

        // actualizar stock
        for (int i = 0; i < items.length; i++) {
            ItemPedido it = (ItemPedido) items[i];
            Producto prod = inventario.buscarProducto(it.getCodigoProducto());
            inventario.actualizarStock(prod.getCodigo(), prod.getStock() - it.getCantidad());
        }

        p.setEstado(EstadoPedido.EN_PROCESO);
        System.out.println("Atendiendo pedido de " + p.getNombreCliente() + " con prioridad " + p.getPrioridad());
        return true;        
    }
    
    // Busqueda por ID
    public Pedido buscarPorId(String id) {
        if (id == null) {
            return null;
        }
        Pedido[] arr = cola.pendientes();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getId().equals(id)) {
                return arr[i];
            }
        }
        return null;
    }
    
    // Lista pedidos pendientes en orden de prioridad
    public void listarPedidoPendientes() {
        Pedido[] arr = cola.pendientes();
        for (int i = 0; i < arr.length; i++) {
            Pedido p = arr[i];
            System.out.println(p);
        }
    }

    public int totalPedidosPorTipo(TipoCliente tipo) {
        int c = 0;
        Pedido[] arr = cola.pendientes();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getTipoCliente() == tipo) {
                c++;
            }
        }
        return c;
    }

    public Pedido pedidoMayorValor() {
        Pedido[] arr = cola.pendientes();
        if (arr.length == 0) {
            return null;
        }
        Pedido max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].getTotal() > max.getTotal()) {
                max = arr[i];
            }
        }
        return max;
    }

    public double promedioProductosPorPedido() {
        Pedido[] arr = cola.pendientes();
        if (arr.length == 0) {
            return 0.0;
        }
        double suma = 0;
        for (int i = 0; i < arr.length; i++) {
            suma += arr[i].getListaItems().tamano();
        }
        return suma / arr.length;
    }
}
