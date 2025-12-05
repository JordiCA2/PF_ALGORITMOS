package modelos;

// Entidad de categoria, agrupa productos por caracteristicas
public class Categoria {

    private String id;
    private String nombre;
    private String descripcion;
    private String caracteristicas;
    private int cantidadProductos;
    
    // Constructor que define datos basicos de la categoria
    public Categoria(String id, String nombre, String descripcion, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.cantidadProductos = 0;
    }

    // Getters y setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id= id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCaracteristicas() {
        return caracteristicas;
    }
    
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return "Categoria: " + nombre + " | " + descripcion;
    }
}
