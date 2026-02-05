package domain;

import Exceptions.InventarioVacioException;

public class Jugador{
    public final static int TAM_INV = 10;
    private Objeto[] inventario;
    private int habitacionActual;

    public Jugador() {
        this.inventario = new Objeto[TAM_INV];
        setHabitacionActual(habitacionActual);
    }

    public Objeto[] getInventario() {
        return inventario;
    }
    public void setInventario(Objeto[] inventario) {
        this.inventario = inventario;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public void setHabitacionActual(int habitacionSiguiente) {
        if (habitacionSiguiente >= 0 && habitacionSiguiente <= 8){
            this.habitacionActual =  habitacionSiguiente;
        }
    }

    public boolean inventarioVacio() {
        int contador = 0;
        for (int i = 0; i < getInventario().length; i++) {
            if (getInventario()[i] != null) {
                contador++;
            }
        }

        return contador == 0;
    }

    public String inventarioActual() throws InventarioVacioException {
        int contador = 0;
        for (int i = 0; i < getInventario().length; i++) {
            if (getInventario()[i] != null) {
                contador++;
            }
        }

        if (contador == 0) {
            throw new InventarioVacioException("El inventario está vacío");
        } else {
            for (int i = 0; i < getInventario().length; i++) {
                if (getInventario()[i] != null) {
                    return (i + 1 + ") " +getInventario()[i]);
                }
            }
        }
        return " ";
    }

    public String  mostrarObjetosLeibles(){
        int contador = 1;
        StringBuilder contenido = new StringBuilder();

        for (int i = 0; i < inventario.length; i++) {
            if(inventario[i]!= null && inventario[i] instanceof Leible){
                contenido.append(contador++).append(". ").append(inventario[i].getNombre()).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }

}