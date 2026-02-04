package domain;

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

}