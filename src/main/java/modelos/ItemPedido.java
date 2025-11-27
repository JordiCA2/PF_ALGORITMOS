package modelos;


public class ItemPedido {
    private String codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

/*  
    // Constructor: calcula subtotal como cantidad * precioUnitario
    public ItemPedido(String codigoProducto, String nombreProducto, int cantidad, double precioUnitario, double subtotal) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad <0 ? 0 : cantidad;
        this.precioUnitario = precioUnitario < 0 ? 0.0 : precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }
*/

    // Constructor: acepta subtotal explicito, si es negativo, se recalcula
    public ItemPedido(String codigoProducto, String nombreProducto, int cantidad, double precioUnitario, double subtotal) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad < 0 ? 0 : cantidad;
        this.precioUnitario = precioUnitario < 0 ? 0.0 : precioUnitario;
        double s = subtotal < 0 ? (this.cantidad * this.precioUnitario) : subtotal;
        this.subtotal = s;
    }    
    
    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) {this.nombreProducto = nombreProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad < 0 ? 0 : cantidad; this.subtotal = this.cantidad * this.precioUnitario; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario < 0 ? 0.0 : precioUnitario; this.subtotal = this.cantidad * this.precioUnitario; }
    public double getSubTotal() { return subtotal; }
    
    // Representaccion breve del item para listados
    public String toString() {
        return String.format("ItemPedido[codigo=%s, nomnbre=%s, cant=%d, pu=%.2f, subtotal=%2f]",
                codigoProducto, nombreProducto, cantidad, precioUnitario, subtotal);
    }
}
