package main.java.domain;


public class Habitacion{
    private final int OBJETOS_MAX = 5;
    private Objeto[] objetosHabitacion;
    private String descripcion;


    public Habitacion(String descripcion) {
        this.descripcion = descripcion;
        objetosHabitacion = new Objeto[OBJETOS_MAX];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Objeto[] getObjetosHabitacion() {
        return objetosHabitacion;
    }

    public void addObjeto(Objeto o){
        for (int i = 0; i < objetosHabitacion.length; i++) {
            if (objetosHabitacion[i] == null){
                objetosHabitacion[i] = o;
                return;
            }

        }
    }

    public String mirar() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.descripcion).append("\n");
        for(Objeto obj : objetosHabitacion) {
            if(obj != null) {
                sb.append(" - ").append(obj.getNombre()).append("\n");
            }
        }
        return sb.toString();
    }

    public Objeto buscar(String nombre) {
        for (Objeto obj : objetosHabitacion) {
            if (obj != null && obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;
    }

    public boolean eliminarObjeto(Objeto obj) {
        for(int i = 0; i < objetosHabitacion.length; i++) {
            if(objetosHabitacion[i] != null && objetosHabitacion[i].equals(obj)) {
                objetosHabitacion[i] = null;
                return true;
            }
        }

        return false;
    }

}
