package domain;

import Exceptions.ObjetoNoCombinableException;

public class Lija extends Item implements Combinable{
    public Lija(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre, visible);
    }

    @Override
    public Objeto combinar(Item otro) throws ObjetoNoCombinableException {
        if (otro != null){
            if(otro instanceof LlaveOxidada llaveOxidada){
                llaveOxidada.combinar(this);
                return new Llave("Una llave con un dorado reluciente", "Llave", true,"11");
            }
        }
        throw new ObjetoNoCombinableException("Falta algun objeto para poder combinar");
    }
}
