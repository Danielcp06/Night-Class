package aventura.app;

public class Jugador{
    private Objeto[] inventario;
    private int habitacionActual;

    public Jugador(Objeto[] inventario, int habitacionActual) {
        this.inventario = inventario;
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