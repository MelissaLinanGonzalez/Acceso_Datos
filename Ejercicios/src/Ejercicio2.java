import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio2 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/eje2.txt");
        int suma = 0;
        double media = 0;
//        int contador = 0;
//        try {
//            BufferedReader lector = new BufferedReader(new FileReader(archivo));
//
//            String linea;
//            while ((linea = lector.readLine()) != null){
//                int numero = Integer.parseInt(linea);
//                suma += numero;
//                contador ++;
//            }
//
//            System.out.println("Suma total: " + suma);
//            media = (double) suma / contador;
//            System.out.println("Media: " + media);
//        } catch (IOException e){
//            System.out.println("Error: " + e.getMessage());
//        }

        ArrayList<Integer> numeros = new ArrayList<>();

        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = lector.readLine()) != null){
                int numero = Integer.parseInt(linea);
                numeros.add(numero);
            }

            for (int numero : numeros){
                suma += numero;
            }
            media = (double) suma / numeros.size();

            System.out.println("Suma total de los números: " + suma);
            System.out.println("Media de los números: " + media);

        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
