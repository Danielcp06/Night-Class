package domain;

import Exceptions.ObjetoNoCombinableException;

public interface Combinable {
    Objeto combinar(Item otro) throws ObjetoNoCombinableException;
}
