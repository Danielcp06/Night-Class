package main.java.domain;

public class Item extends Objeto implements domain.Inventariable {

    public Item(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre, visible);
    }

}
