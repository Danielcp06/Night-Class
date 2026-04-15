package main.java.domain;

public class Contenedor extends Mueble implements Abrible {
    private String codigoNecesario; // Si es null, se abre sin llave.
    private Objeto objetoContenido; // El "premio" que hay dentro.
    private boolean abierto;

    public Contenedor(String nombre, String descripcion, String codigoNecesario, Objeto objetoContenido) {
        super(descripcion, nombre, true);
        this.codigoNecesario = codigoNecesario;
        this.objetoContenido = objetoContenido;
        this.abierto = false;
    }

    @Override
    public RespuestaAccion abrir(Llave llave) {
        // 1. Comprobar si ya está abierto
        if (abierto) {
            return new RespuestaAccion("El " + getNombre() + " ya está abierto.",false );
        }

        // 2. Lógica de apertura (Si no necesita llave O si la llave es la correcta)
        if (codigoNecesario == null) {
            this.abierto = true;
            return new RespuestaAccion("Has abierto el " + getNombre() + " con las manos.",true );
        }

        if (llave != null && llave.getCodigoDeSeguridad().equals(codigoNecesario)) {
            this.abierto = true;
            return new RespuestaAccion("¡Click! La llave " + llave.getNombre() + " encaja perfectamente.",true );
        }

        // 3. Si nada de lo anterior funciona, es que está cerrado
        return new RespuestaAccion("El " + getNombre() + " está cerrado bajo llave.",false );
    }

    @Override
    public boolean estaAbierto() {
        return abierto;
    }

    @Override
    public String getCodigoNecesario() {
        return codigoNecesario;
    }

    @Override
    public Objeto getContenido() {
        return objetoContenido;
    }

    @Override
    public void setContenido(Objeto contenido) {
        this.objetoContenido = contenido;
    }

    @Override
    public void cerrar() {
        this.abierto = false;
    }

    public Objeto getObjetoContenido() {
        return objetoContenido;
    }
}
