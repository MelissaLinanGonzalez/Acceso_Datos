package Ejercicio_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/Ejercicio_4/empleado.txt");

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            int i = 1;
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split(";");
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                String puesto = datos[2];

                Empleado empleado = new Empleado(nombre, edad, puesto);
                System.out.println("Empleado " + i + " " + empleado);
                i++;
            }

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
