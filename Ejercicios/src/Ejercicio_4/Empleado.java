package Ejercicio_4;

public class Empleado {
    private String nombre;
    private int edad;
    private String puesto;

    public Empleado(String nombre, int edad, String puesto){
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "{nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", puesto='" + puesto + '\'' +
                '}';
    }
}
