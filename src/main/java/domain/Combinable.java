package domain;

import Exceptions.ObjetoNoCombinableException;

public interface Combinable {
    Objeto combinar(Objeto otro) throws ObjetoNoCombinableException;
}
