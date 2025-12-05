package modelos;

import java.time.LocalDateTime;

public class Operacion {

    private String tipo;         // Tipo de operación (AGREGAR_PRODUCTO, REGISTRAR_PEDIDO, etc)
    private LocalDateTime fecha; // Fecha y hora de la operación
    private Object antes;        // Estado anterior (si aplica)
    private Object despues;      // Estado nuevo (si aplica)
    private static int SEQ = 1;
    private int id;
    private TipoOperacion tipoOp;
    private String descripcion;
    private String detalles;    // JSON/String
    private String usuario;
    
    public Operacion(TipoOperacion tipoOp, String descripcion, String detalles, String usuario, Object antes, Object despues) {
        this.id = SEQ++;
        this.tipoOp = tipoOp;
        this.descripcion = descripcion;
        this.detalles = detalles;
        this.usuario = usuario;
        this.tipo = tipo;
        this.antes = antes;
        this.despues = despues;
        this.fecha = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public TipoOperacion getTipoOp() {
        return tipoOp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDetalles() {
        return detalles;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Object getAntes() {
        return antes;
    }

    public Object getDespues() {
        return despues;
    }

    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo;
    }
}
