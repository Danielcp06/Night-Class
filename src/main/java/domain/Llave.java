package main.java.domain;

public class Llave extends Item {
    private String codigoDeSeguridad;
    public Llave(String descripcion, String nombre, boolean visible, String codigoDeSeguridad) {
        super(descripcion, nombre, visible);
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public String getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

}
