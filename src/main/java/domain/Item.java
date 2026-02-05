package domain;

public class Item extends Objeto implements Inventariable{

    public Item(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre, visible);
    }

}
