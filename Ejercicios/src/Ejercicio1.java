import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        File archivo = new File("Ejercicios/src/eje1.txt");
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = lector.readLine())!= null){
                String[] datos = linea.split(":");
                String nombre = datos[1];
                System.out.println(nombre);
            }
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
