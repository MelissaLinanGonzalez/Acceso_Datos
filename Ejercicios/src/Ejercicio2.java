import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/eje2.txt");
        int suma = 0;
        double media = 0;
        int contador = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            String linea;
            while ((linea = lector.readLine()) != null){
                int numero = Integer.parseInt(linea);
                suma += numero;
                contador ++;
            }

            System.out.println("Suma total: " + suma);
            media = (double) suma / contador;
            System.out.println("Media: " + media);
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
