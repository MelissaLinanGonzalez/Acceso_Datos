package Ejercicio_5;

import java.io.*;
import java.util.Arrays;

public class Ejercicio5 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/Ejercicio_5/productos.txt");
        File archivoNuevo = new File("Ejercicios/src/Ejercicio_5/productos_actualizados.txt");

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoNuevo));

            String linea;
            while ((linea = lector.readLine()) != null){
                String[] datos = linea.split(";");
                double precio = Double.parseDouble(datos[2]);
                double precioNuevo = precio * 1.10;
                datos[2] = Double.toString(precioNuevo);

                escritor.write(Arrays.toString(datos) + "\n");
            }
            escritor.close();
            lector.close();
            System.out.println("Datos actualizados correctamente");
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
