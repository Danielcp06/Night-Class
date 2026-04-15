package main.java.domain;


import main.java.Exceptions.ObjetoNoCombinableException;

public class Lija extends Item implements Combinable{
    public Lija(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre, visible);
    }

    @Override
    public Objeto combinar(Objeto otro) throws ObjetoNoCombinableException {
        if (otro != null){
            if(otro instanceof LlaveOxidada llaveOxidada){
                return llaveOxidada.combinar(this);
            }
        }
        throw new ObjetoNoCombinableException("Falta algun objeto para poder combinar");
    }
}
