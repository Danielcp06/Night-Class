package main.java.domain;

public interface Abrible {
    RespuestaAccion abrir(Llave llave);
    boolean estaAbierto();
    String getCodigoNecesario();
    Objeto getContenido();
    void setContenido(Objeto contenido);
    void cerrar();
}
