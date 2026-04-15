package main.java.domain;


import main.java.Exceptions.ObjetoNoCombinableException;

public interface Combinable {
    Objeto combinar(Objeto otro) throws ObjetoNoCombinableException;
}
