package aventura.app;

public abstract class Objeto extends Entidad{
    private boolean visible;

    public Objeto(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre);
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
