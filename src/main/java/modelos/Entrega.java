package modelos;

import java.time.LocalDateTime;

public class Entrega {

    private String idPedido;
    private String idEntrega;
    private Pedido pedido;
    private String direccion;
    private String distrito;
    private String repartidor;

    private LocalDateTime horaEstimada;
    private LocalDateTime horaReal;
    private EstadoEntrega estado;

    private long tiempoEntregaMinutos;

    public Entrega(String idEntrega, Pedido pedido, String direccion, String distrito,
            String repartidor, LocalDateTime horaEstimada) {
        this.idPedido = pedido != null ? pedido.getId() : null; // opcional
        this.idEntrega = idEntrega;
        this.pedido = pedido;
        this.direccion = direccion;
        this.distrito = distrito;
        this.repartidor = repartidor;
        this.horaEstimada = horaEstimada;
        this.estado = EstadoEntrega.PENDIENTE;
        this.horaReal = null;
        this.tiempoEntregaMinutos = -1;
    }

    /**
     * Marca la entrega como completada
     */
    public void marcarEntregado(LocalDateTime now) {

        this.horaReal = now;

        // Cambiar estado
        this.estado = EstadoEntrega.ENTREGADO;

        if (this.horaEstimada != null) {
            this.tiempoEntregaMinutos
                    = java.time.Duration.between(this.horaEstimada, this.horaReal).toMinutes();
        } else {
            this.tiempoEntregaMinutos = -1;
        }

        if (this.pedido != null) {
            this.pedido.setEstado(EstadoPedido.ENTREGADO);
        }
    }

    /**
     * Getter estándar
     */
    public long getTiempoEntregaMinutos() {
        return tiempoEntregaMinutos;
    }

    /**
     * Método adicional requerido por GestorEntregas
     */
    public long tiempoEntregaMinutos() {
        return tiempoEntregaMinutos;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public EstadoEntrega getEstado() {
        return estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setRepartidor(String r) {
        this.repartidor = r;
    }

    public void setEstado(EstadoEntrega e) {
        this.estado = e;
    }

    public void setHoraEstimada(LocalDateTime h) {
        this.horaEstimada = h;
    }

    @Override
    public String toString() {
        return "Entrega[" + idEntrega + "] Pedido=" + (pedido != null ? pedido.getId() : "N/A")
                + " | " + direccion
                + " | Dist=" + distrito
                + " | Rep=" + repartidor
                + " | Estado=" + estado;
    }
}
