package domain;

public class Nota extends Item implements Leible {
    private String contenido;

    public Nota(String nombre, String descripcion, String contenido) {
        super(descripcion, nombre, true);
        this.contenido = contenido;
    }

    @Override
    public String leer() {
        return this.contenido; // Aquí es donde la nota entrega su texto
    }
}