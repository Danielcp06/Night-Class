package aventura.app;

public class Habitacion{
    private final int OBJETOS_MAX = 5;
    private Objeto[] objetosHabitacion;
    private String descripcion;

    public Habitacion(String descripcion) {
        this.descripcion = descripcion;
        objetosHabitacion = new Objeto[OBJETOS_MAX];
    }

    public String getDescripcion() {
        return descripcion;
    }
}
