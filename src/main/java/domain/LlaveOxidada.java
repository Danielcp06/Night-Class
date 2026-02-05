package domain;

import Exceptions.ObjetoNoCombinableException;

public class LlaveOxidada extends Item implements Combinable{
    public LlaveOxidada(String descripcion, String nombre, boolean visible) {
        super(descripcion, nombre, visible);
    }

    @Override
    public Objeto combinar(Objeto otro) throws ObjetoNoCombinableException {
        if (otro != null){
            if (otro instanceof Lija){
                return new Llave("Una llave con un dorado reluciente", "Llave", true, "11");
            }
        }
        throw new ObjetoNoCombinableException("Falta algun objeto para poder combinar");
    }
}
