package aventura.app;

public abstract class Entidad {
    private String descripcion;
    private String nombre;

    public Entidad(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }
}
