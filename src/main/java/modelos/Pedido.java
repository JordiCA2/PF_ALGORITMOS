package modelos;

import estructuras.ListaEnlazada;
import java.time.LocalDateTime;

// Pedido con prioridad automatica, fecha y total
public class Pedido {
    private String id;
    private String nombreCliente;
    private String direccion;
    private LocalDateTime fechaRegistro;
    private TipoCliente tipoCliente;
    private TipoEntrega tipoEntrega;
    private EstadoPedido estado;
    private int prioridad;
    private double total; // total del pedido con descuento
    private ListaEnlazada<ItemPedido> listaItems;
    
    public Pedido(String id, String nombreCliente, String direccion, TipoCliente tipoCliente, TipoEntrega tipoEntrega) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
        this.tipoEntrega = tipoEntrega;
        this.estado = EstadoPedido.PENDIENTE;
        this.prioridad = calcularPrioridad(tipoCliente, tipoEntrega);
        this.fechaRegistro = LocalDateTime.now();
        this.total = 0.0;
        this.listaItems = new ListaEnlazada<>();
    }
    
    // Agrega un item y actualiza el total
    public void agregarItem(ItemPedido item) {
        this.listaItems.agregar(item);
        recalcularTotal();
    }
    
    // Recalcula el total sumando subtotales y aplica 10% si es PREMIUM
    public void recalcularTotal() {
        double suma = 0.0;
        Object[] arr = this.listaItems.toArray();
        for (int i = 0; i < arr.length; i++) {
            ItemPedido it = (ItemPedido) arr[i];
            suma += it.getSubTotal();
        }
        if (this.tipoCliente == TipoCliente.PREMIUM) suma *= 0.9;
        this.total = suma;
    }
    
    // Calcula prioridad segun tipo de cliente y entrega
    private int calcularPrioridad(TipoCliente c, TipoEntrega e) {
        if (c == TipoCliente.PREMIUM && e == TipoEntrega.EXPRESS) return 10;
        if (c == TipoCliente.PREMIUM && e == TipoEntrega.NORMAL) return 8;
        if (c == TipoCliente.REGULAR && e == TipoEntrega.EXPRESS) return 7;
        return 5;
    }
    
    public String getId() { return id; }
    public String getNombreCliente() { return nombreCliente; }
    public String getDireccion() { return direccion; }
    public LocalDateTime getFechaCliente() { return fechaRegistro; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public TipoEntrega getTipoEntrega() { return tipoEntrega; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public int getPrioridad() { return prioridad; }
    public double getTotal() { return total; }
    public ListaEnlazada<ItemPedido> getListaItems() { return listaItems; }
    
    // Representacion breve para imprimir en listas
    public String toString() {
        return String.format("Pedido[id=%s, cliente=%s, prioridad=%d, estado=%s, total=%.2f]",
                id, nombreCliente, prioridad, estado.name(), total);
    }
}
