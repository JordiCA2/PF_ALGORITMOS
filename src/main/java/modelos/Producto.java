package modelos;

public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
    private boolean esBiodegradable;
    private String materialesReciclados;
    
    public Producto(String codigo, String nombre, String categoria, double precio, int stock, boolean esBiodegradable, String materialesReciclados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.esBiodegradable = esBiodegradable;
        this.materialesReciclados = materialesReciclados;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public boolean isEsBiodegradable() {
        return esBiodegradable;
    }
    
    public void setEsBiodegradable(boolean esBiodegradable) {
        this.esBiodegradable = esBiodegradable;
    }
    
    public String getMaterialesReciclados() {
        return materialesReciclados;
    }
    
    public void setMaterialesReciclados(String materialesReciclados) {
        this.materialesReciclados = materialesReciclados;
    }
    
    public String toString() {
        return String.format(
        "Producto[codigo=%s, nombre=%s, categoria=%s, precio=%.2f, stock=%d, esBiodegradable=%s, materialesReciclados=%s]",
        codigo, nombre, categoria, precio, stock, esBiodegradable ? "si" : "no", materialesReciclados        
        );
    }
}
