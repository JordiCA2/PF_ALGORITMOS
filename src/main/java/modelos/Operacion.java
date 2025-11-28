package modelos;

import java.time.LocalDateTime;

public class Operacion {

    private String tipo;         // Tipo de operación (AGREGAR_PRODUCTO, REGISTRAR_PEDIDO, etc)
    private LocalDateTime fecha; // Fecha y hora de la operación
    private Object antes;        // Estado anterior (si aplica)
    private Object despues;      // Estado nuevo (si aplica)

    public Operacion(String tipo, Object antes, Object despues) {
        this.tipo = tipo;
        this.antes = antes;
        this.despues = despues;
        this.fecha = LocalDateTime.now();
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
